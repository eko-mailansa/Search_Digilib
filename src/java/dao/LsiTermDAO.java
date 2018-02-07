/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static Util.DBUtil.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import controller.LsiTermBean;
import java.sql.SQLException;
import java.util.StringTokenizer;

/**
 *
 * @author AnsaKhitara
 */
public class LsiTermDAO {

    public static Statement stmtObj;
    public static Connection connObj;
    public static ResultSet resultSetObj;
    public static PreparedStatement pstmt;

    /* Method To Fetch The Term Records From Database */
    public static ArrayList<LsiTermBean> getTermListFromDB(){
        ArrayList<LsiTermBean> termList = new ArrayList<LsiTermBean>();
        try {
            stmtObj = getConnection().createStatement();
            resultSetObj = stmtObj.executeQuery("select * from term");
            while (resultSetObj.next()) {
                LsiTermBean tObj = new LsiTermBean();
                tObj.setId(resultSetObj.getInt("id"));
                tObj.setTerm(resultSetObj.getString("term"));
                termList.add(tObj);
            }
            System.out.println("Total Records Fetched: " + termList.size());
            connObj.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return termList;
    }
    
//    /* Method Used To Save New Term Record In Database */
//    public static String saveTermDetailsInDB(LsiTermBean newTermObj) throws SQLException {
//        int saveResult = 0;
//        String term = newTermObj.getTerm();
//        StringTokenizer st = new StringTokenizer(title);
//        ArrayList<String> tokenizer = new ArrayList<String>();
//        while (st.hasMoreElements()) {
//            tokenizer.add(st.nextToken());
//        }
//        for (int i = 0; i < tokenizer.size(); i++) {
//            stmtObj = getConnection().createStatement();
//            resultSetObj = stmtObj.executeQuery("select * from term WHERE term = '"+tokenizer.get(i)+"'");
//            if(!resultSetObj.first()){
//                pstmt = getConnection().prepareStatement("insert into term (term) values (?)");
//                pstmt.setString(1, tokenizer.get(i));
//                saveResult = pstmt.executeUpdate();
//            }
//        }
//        String navigationResult = "";
//        try {
//            pstmt = getConnection().prepareStatement("insert into dokumen (title, alamat) values (?, ?)");
//            pstmt.setString(1, newTermObj.getTitle());
//            pstmt.setString(2, newTermObj.getAlamat());
//            saveResult = pstmt.executeUpdate();
//            connObj.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        if (saveResult != 0) {
//            navigationResult = "/Home_Term.xhtml?faces-redirect=true";
//        } else {
//            navigationResult = "/operation/term/createTerm.xhtml?faces-redirect=true";
//        }
//        return navigationResult;
//    }
}