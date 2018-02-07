/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AuthorRDFDAO;
import dao.SearchAuthorRDFDAO;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.AuthorRDF;

/**
 *
 * @author AnsaKhitara
 */
@ManagedBean
@SessionScoped
public class AuthorRDFManagedBean {

    private String nama_author;
    private String search;

    public String getNama_author() {
        return nama_author;
    }

    public void setNama_author(String nama_author) {
        this.nama_author = nama_author;
    }
    
    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    //Show all
    public ArrayList<AuthorRDF> getAuthorRDFShow() {
        return new AuthorRDFDAO().getAllAuthorRDF();
    }

    //Show by nama_author
    public void getAllByAuthorShow(String nama_author) {
        this.nama_author = nama_author;
    }
   
    //Search database by Nama_Author
    public ArrayList<AuthorRDF> getSearchRDFAuthor() {
        return new SearchAuthorRDFDAO().SearchRDFAuthor(search);
    }
//    
//    public boolean isURIResource(RDFNode node){
//        return node.isURIResource();
//        return false;
//    }
}
