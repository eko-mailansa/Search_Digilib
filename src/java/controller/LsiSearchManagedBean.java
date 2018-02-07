/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ArticleRDFDAO;
import dao.LsiSearching;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.ArticleRDF;
import model.Lsi;

/**
 *
 * @author AnsaKhitara
 */
@ManagedBean
@SessionScoped
public class LsiSearchManagedBean {
    private String search;

    public void setSearch(String search) {
        this.search = search;
    }

    public String getSearch() {
        return search;
    }
    
    //Search database by Title
    public ArrayList<Lsi> getSearchLsiArticle() {
        return new LsiSearching().getAllArticleLsi(search);
    }

}
