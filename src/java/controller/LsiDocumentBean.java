/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.LsiDocumentDAO;
import model.LsiDocument;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author AnsaKhitara
 */
@ManagedBean
@RequestScoped
public class LsiDocumentBean {

    private int id;
    private String title, alamat;

    public ArrayList<LsiDocumentBean> documentListFromDB;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    

    public ArrayList<LsiDocumentBean> getDocumentListFromDB() {
        return documentListFromDB;
    }

    public void setDocumentListFromDB(ArrayList<LsiDocumentBean> documentListFromDB) {
        this.documentListFromDB = documentListFromDB;
    }    

    /* Method Will Avoid Multiple Calls To DB For Fetching The Students Records. If This Is Not Used & Data Is Fetched From Getter Method, JSF DataTable Will Make Multiple Calls To DB*/
    @PostConstruct
    public void init() {
        documentListFromDB = LsiDocumentDAO.getDocumentListFromDB();
    }

    /* Method Used To Fetch All Records From The Database */
    public ArrayList<LsiDocumentBean> documentList() {
        return documentListFromDB;
    }

    /* Method Used To Save New Student Record */
    public String saveDocumentDetails(LsiDocumentBean newDocumentObj) throws SQLException {
        return LsiDocumentDAO.saveDocumentDetailsInDB(newDocumentObj);
    }
    
}
