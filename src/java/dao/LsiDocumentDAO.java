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

import controller.LsiDocumentBean;
import java.sql.SQLException;
import java.util.StringTokenizer;

/**
 *
 * @author AnsaKhitara
 */
public class LsiDocumentDAO {

    public static Statement stmtObj;
    public static Connection connObj;
    public static ResultSet resultSetObj;
    public static PreparedStatement pstmt;

    /* Method To Fetch The Document Records From Database */
    public static ArrayList<LsiDocumentBean> getDocumentListFromDB(){
        ArrayList<LsiDocumentBean> documentList = new ArrayList<LsiDocumentBean>();
        try {
            stmtObj = getConnection().createStatement();
            resultSetObj = stmtObj.executeQuery("select * from dokumen");
            while (resultSetObj.next()) {
                LsiDocumentBean tObj = new LsiDocumentBean();
                tObj.setId(resultSetObj.getInt("id"));
                tObj.setTitle(resultSetObj.getString("title"));
                tObj.setAlamat(resultSetObj.getString("alamat"));
                documentList.add(tObj);
            }
            System.out.println("Total Records Fetched: " + documentList.size());
            connObj.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return documentList;
    }
    
    /* Method Used To Save New Document Record In Database */
    public static String saveDocumentDetailsInDB(LsiDocumentBean newDocumentObj) throws SQLException {
        int saveResult = 0;
        String title = newDocumentObj.getTitle();
        StringTokenizer st = new StringTokenizer(title);
        ArrayList<String> tokenizer = new ArrayList<String>();
        while (st.hasMoreElements()) {
            tokenizer.add(st.nextToken());
        }
        for (int i = 0; i < tokenizer.size(); i++) {
            stmtObj = getConnection().createStatement();
            resultSetObj = stmtObj.executeQuery("select * from term WHERE term = '"+tokenizer.get(i)+"'");
            if(!resultSetObj.first()){
                pstmt = getConnection().prepareStatement("insert into term (term) values (?)");
                pstmt.setString(1, tokenizer.get(i));
                saveResult = pstmt.executeUpdate();
            }
        }
        String navigationResult = "";
        try {
            pstmt = getConnection().prepareStatement("insert into dokumen (title, alamat) values (?, ?)");
            pstmt.setString(1, newDocumentObj.getTitle());
            pstmt.setString(2, newDocumentObj.getAlamat());
            saveResult = pstmt.executeUpdate();
            connObj.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (saveResult != 0) {
            navigationResult = "/Home_LsiDocument.xhtml?faces-redirect=true";
        } else {
            navigationResult = "/operation/LSI/document/createDocument.xhtml?faces-redirect=true";
        }
        return navigationResult;
    }
}