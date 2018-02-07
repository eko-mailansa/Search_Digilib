/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ArticleRDFDAO;
import dao.SearchRDFDAO;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.ArticleRDF;
import org.apache.jena.rdf.model.RDFNode;

/**
 *
 * @author AnsaKhitara
 */
@ManagedBean
@SessionScoped
public class ArticleRDFManagedBean {

    private RDFNode title;
    private String search;

    public RDFNode getTitle() {
        return title;
    }

    public void setTitle(RDFNode title) {
        this.title = title;
    }

    
    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    //Show all
    public ArrayList<ArticleRDF> getArticleRDFShow() {
        return new ArticleRDFDAO().getAllArticleRDF();
    }

    //Show by id
    public void getAllByArticleShow(RDFNode title) {
        this.title = title;
    }

    //Reset setter null;
//    public void resetValue() {
//        //Reset update
//        this.title = "";
//    }

    //Search database by Title
    public ArrayList<ArticleRDF> getSearchRDFArticle() {
        return new SearchRDFDAO().SearchRDFArticle(search);
    }
//    
//    public boolean isURIResource(RDFNode node){
//        return node.isURIResource();
//        return false;
//    }
}
