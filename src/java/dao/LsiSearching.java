/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Jama.Matrix;
import Jama.SingularValueDecomposition;
import Util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import model.Lsi;

/**
 *
 * @author AnsaKhitara
 */
public class LsiSearching {
    static final int NUM_FACTORS = 1;
    static final int NUM_FACTORS1 = 4;
    
    public String[] getById(double Id){
        Connection conn = DBUtil.getConnection();
        String sql = "SELECT * FROM dokumen WHERE id = '"+Id+"'";
        String [] result = new String[2];
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result[0] = rs.getString("title");
                result[1] = rs.getString("alamat");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static void sortbyColumn(double arr[][], int col)
    {
        // Using built-in sort function Arrays.sort
        Arrays.sort(arr, new Comparator<double[]>() {
           
          @Override             
          // Compare values according to columns
          public int compare(final double[] entry1, 
                             final double[] entry2) {
 
            // To sort in descending order revert 
            // the '>' Operator
            if (entry1[col] < entry2[col])
                return 1;
            else
                return -1;
          }
        });  // End of function call sort().
    }

    public ArrayList<Lsi> getAllArticleLsi(String keyword) {
        ArrayList<Lsi> AllArticleList = new ArrayList();

//    public class term {
        double featureInit = 0.01;
        double initialLearningRate = 0.005;
        int annealingRate = 1000;
        double regularization = 0.00;
        double minImprovement = 0.0000;
        int minEpochs = 10;
        int maxEpochs = 50000;
        int i, j, k;

        List<String> document30 = new ArrayList<String>();
        List<String> term = new ArrayList<String>();
        Connection conn = DBUtil.getConnection();
        String sql = "SELECT * FROM dokumen WHERE title LIKE '%"+keyword+"%' order by 'id' DESC";
        String title_temp = null;
        String alamat_temp = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                title_temp = rs.getString("title");
                alamat_temp = rs.getString("alamat");
                String document30_temp = rs.getString("title");
                document30.add(document30_temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if (document30.isEmpty()){
            return AllArticleList;
        } else if (document30.size() == 1) {
            Lsi Al = new Lsi(title_temp,alamat_temp);
            AllArticleList.add(Al);
            return AllArticleList;
        }
//        System.out.println(document30);
//        System.out.println("=======================================================================");

        List<Double> search = new ArrayList<Double>();
        String string_search = keyword.replaceAll("\\,", "");
        String[] split_search = string_search.split(" ");
        String sql_term = "SELECT * FROM term order by 'id' ASC";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql_term);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String term_temp = rs.getString("term");
                int counter_search = 0;
                for (k = 0; k < split_search.length; ++k) {
                    if (term_temp.equals(split_search[k])) {
                        counter_search++;
                    }
                }
                search.add((double) counter_search);
                term.add(term_temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        double[][] src = new double[search.size()][];
//        
//        for (i = 0; i < search.size(); ++i) {
//            src[i][0] = search.get(i);
//        }
//        System.out.println(search);
//        System.out.println("+++===========================================");
        final double[][] tdm = new double[term.size()][document30.size()];
//        System.out.println(document30.size());
//        System.out.println(tdm[0].length);
//        System.out.println(term.size());

        for (i = 0; i < term.size(); ++i) {
//            List<Double> term_document30_matrix_temp = new ArrayList<Double>();
            for (j = 0; j < document30.size(); ++j) {
                String string_dok = document30.get(j).replaceAll("\\,", "");
                String[] split_dok = string_dok.split(" ");
                int counter = 0;
                for (k = 0; k < split_dok.length; ++k) {
                    if (term.get(i).toLowerCase().equals(split_dok[k].toLowerCase())) {
                        counter++;
                    }
                }
//                System.out.println(j);
//                System.out.println(i);

                tdm[i][j] = counter;
            }
        }
//        System.out.println(tdm.length);
        System.out.println("=======================================================================");

//        
        //Dekomposisi MAtrix
        System.out.println("\n\nLangkah 2: Mendekomposisi Matriks A (A = USV(Transpose))");
        System.out.println("========================================================");

        Matrix A = new Matrix(tdm);
        SingularValueDecomposition s = A.svd();
        A.print(4, 4);
        // compute the singular vallue decomposition
        System.out.println("A = U S V^T");
        System.out.println();
        System.out.print("U = ");
        Matrix U = s.getU();
        U.print(4, 4);
        System.out.print("Sigma = ");
        Matrix S = s.getS();
        S.print(20, 4);
        System.out.print("V = ");
        Matrix V = s.getV();
        V.print(20, 4);

        //Rank 2
        System.out.println("Langkah 3: Implementasikan Sebuah Perkiraan Pada Rank 2 Dengan Menjaga Kolom Pertama Dari U Dan V Serta Kolom Dan Baris Pertama Dari S");
        System.out.println("======================================================================================================================================");

        //Rank 2 Matrix U
        int rowMatrixU = U.getRowDimension();
//        System.out.println(rowMatrixU);
        Matrix Unew = U.getMatrix(0, (rowMatrixU - 1), 0, 1);
//        Unew.print(20, 4);

        //Rank 2 Matrix S
        System.out.println("");
        int rowMatrixS = S.getRowDimension();
//        System.out.println(rowMatrixS);
        Matrix Snew = S.getMatrix(0, 1, 0, 1);
//        Snew.print(20, 4);

        //Rank 2 Matrix V
        System.out.println("");
        int rowMatrixV = V.getRowDimension();
//        System.out.println(rowMatrixV);
        Matrix Vnew = V.getMatrix(0, (rowMatrixV - 1), 0, 1);
//        Vnew.print(20, 4);

        //Rank 2 Matrix v Transpose
        Matrix VTnew = Vnew.transpose();
//        VTnew.print(20, 4);
        System.out.println("");

        System.out.println("Langkah 4: Menemukan Koordinat Vektor Dokumen Yang Baru Di Ruang 2 Dimensi Yang Telah Diperkecil");
        System.out.println("================================================================================================");
        
        double[][] VnewArray = new double[Vnew.getRowDimension()][Vnew.getColumnDimension()+1];
       
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            int counterVnew = 0;
            while (rs.next()) {
                VnewArray[counterVnew][0] = rs.getDouble("id");
                for (int l = 0; l < Vnew.getColumnDimension(); l++) {
                    VnewArray[counterVnew][l+1] = Vnew.get(counterVnew, l);
                }
                counterVnew++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        System.out.println("");

        System.out.println("Langkah 5: Menemukan Koordinat Vektor Dokumen Yang Baru Di Ruang 2 Dimensi Yang Telah Diperkecil");
        System.out.println("================================================================================================");

        System.out.println("q = q^T Uk Sk^-1\n");
//        System.out.println("\n");

        //Matrix Q Transpose
        Double src[] = search.toArray(new Double[search.size()]);
//        double[] d = ArrayUtils.toPrimitive(src);
        double[] src_unbox = Stream.of(src).mapToDouble(Double::doubleValue).toArray();
        Matrix Q = new Matrix(src_unbox, search.size());
//        Q.print(2, 4);
        Matrix Qt = Q.transpose();
        Qt.print(2, 4);

        double[][] Sxpon = new double[Snew.getColumnDimension()][Snew.getRowDimension()];
        for (i = 0; i < Snew.getColumnDimension(); ++i) {
            for (j = 0; j < Snew.getRowDimension(); ++j) {
                if (Snew.get(i, j) != 0) {
                    Sxpon[i][j] = Math.pow(Snew.get(i, j), -1);
                }
            }
        }

        //Matrix U Rank 2
        int rowMatrixU5 = U.getRowDimension();
        System.out.println(rowMatrixU);
        Matrix U5new = U.getMatrix(0, (rowMatrixU - 1), 0, 1);
        Unew.print(20, 4);

        //Rank 2 Matrix S pangkat -1
        System.out.println("");
        int rowMatrixS5 = S.getRowDimension();
        System.out.println(rowMatrixS);
        Matrix S5new = S.getMatrix(0, 1, 0, 1);
        Snew.print(20, 4);

        //Reslt Q yang baru
        Matrix Sxponent = new Matrix(Sxpon);
        Sxponent.print(2, 4);
        Matrix QNew = Qt.times(Unew.times(Sxponent));
        QNew.print(2, 4);

        //Peringkat Dokumen
        System.out.println("");

        System.out.println("Langkah 6: Peringkat Dokumen Dalam Urutan Secara Menurun Untuk Dokumen Kueri Persamaan Kosinus");
        System.out.println("==============================================================================================");

        System.out.println("Sim (q, d) = (q + d) / (| q | | d |)");
        System.out.println("\n");

        double[][] hasilAkhir = new double[Vnew.getRowDimension()][2];

        for (int l = 0; l < Vnew.getRowDimension(); l++) {
            hasilAkhir[l][0] = VnewArray[l][0];
            hasilAkhir[l][1] = ((VnewArray[l][1]*QNew.get(0, 0))+(VnewArray[l][2]*QNew.get(0, 1)))/(Math.sqrt((Math.pow(VnewArray[l][1], 2))+((Math.pow(QNew.get(0, 1), 2))))+Math.sqrt((Math.pow(VnewArray[l][2], 2))+((Math.pow(QNew.get(0, 0), 2)))));
        }
        sortbyColumn(hasilAkhir, 1);
        for (int l = 0; l < hasilAkhir.length; l++) {
            String[] temp = getById(hasilAkhir[l][0]);
            Lsi Al = new Lsi(temp[0],temp[1]);
            AllArticleList.add(Al);
        }
        return AllArticleList;
    }
    
}
