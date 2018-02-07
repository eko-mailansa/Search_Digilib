/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.LibraryRDFDAO;
import dao.SearchLibraryRDFDAO;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.LibraryRDF;

/**
 *
 * @author AnsaKhitara
 */
@ManagedBean
@SessionScoped
public class LibraryRDFManagedBean {

    private String isbn;
    private String search;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

        
    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    //Show all
    public ArrayList<LibraryRDF> getLibraryRDFShow() {
        return new LibraryRDFDAO().getAllLibraryRDF();
    }

    //Show by ISBN
    public void getAllByIsbnShow(String isbn) {
        this.isbn = isbn;
    }

    //Reset setter null;
    public void resetValue() {
        //Reset update
        this.isbn = "";
    }

     //Search database by Nama_Library
    public ArrayList<LibraryRDF> getSearchRDFLibrary() {
        return new SearchLibraryRDFDAO().SearchRDFLibrary(search);
    }
}
