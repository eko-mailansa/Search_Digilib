/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.LsiTermDAO;
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
public class LsiTermBean {

    private int id;
    private String term;

    public ArrayList<LsiTermBean> termListFromDB;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public ArrayList<LsiTermBean> getTermListFromDB() {
        return termListFromDB;
    }

    public void setTermListFromDB(ArrayList<LsiTermBean> termListFromDB) {
        this.termListFromDB = termListFromDB;
    }

    /* Method Will Avoid Multiple Calls To DB For Fetching The Students Records. If This Is Not Used & Data Is Fetched From Getter Method, JSF DataTable Will Make Multiple Calls To DB*/
    @PostConstruct
    public void init() {
        termListFromDB = LsiTermDAO.getTermListFromDB();
    }

    /* Method Used To Fetch All Records From The Database */
    public ArrayList<LsiTermBean> termList() {
        return termListFromDB;
    }
}
