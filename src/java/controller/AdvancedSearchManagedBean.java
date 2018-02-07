/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ArticleRDFDAO;
import dao.AuthorRDFDAO;
import dao.LibraryRDFDAO;
import dao.SearchAuthorRDFDAO;
import dao.SearchLibraryRDFDAO;
import dao.SearchRDFDAO;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.ArticleRDF;
import model.AuthorRDF;
import model.LibraryRDF;
import org.apache.jena.rdf.model.RDFNode;

/**
 *
 * @author AnsaKhitara
 */
@ManagedBean
@SessionScoped
public class AdvancedSearchManagedBean implements Serializable {

    private RDFNode title;
    private String nama_author;
    private String isbn;
    private String search;

    public AdvancedSearchManagedBean(RDFNode title, String nama_author, String isbn, String search) {
        this.title = title;
        this.nama_author = nama_author;
        this.isbn = isbn;
        this.search = search;
    }

    public AdvancedSearchManagedBean() {

    }

    public RDFNode getTitle() {
        return title;
    }

    public void setTitle(RDFNode title) {
        this.title = title;
    }

    public String getNama_author() {
        return nama_author;
    }

    public void setNama_author(String nama_author) {
        this.nama_author = nama_author;
    }

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

    //Show all Title
    public ArrayList<ArticleRDF> getArticleRDFShow() {
        return new ArticleRDFDAO().getAllArticleRDF();
    }

    //Show by Title
    public void getAllByArticleShow(RDFNode title) {
        this.title = title;
    }

    //Search database by Title
    public ArrayList<ArticleRDF> getSearchRDFArticle() {
        return new SearchRDFDAO().SearchRDFArticle(search);
    }

    //Show all Nama_Author
    public ArrayList<AuthorRDF> getAuthorRDFShow() {
        return new AuthorRDFDAO().getAllAuthorRDF();
    }

    //Show by Nama_Author
    public void getAllByAuthorShow(String nama_author) {
        this.nama_author = nama_author;
    }

    //Search database by Nama_Author
    public ArrayList<AuthorRDF> getSearchRDFAuthor() {
        return new SearchAuthorRDFDAO().SearchRDFAuthor(search);
    }

    //Show all ISBN
    public ArrayList<LibraryRDF> getLibraryRDFShow() {
        return new LibraryRDFDAO().getAllLibraryRDF();
    }

    //Show by ISBN
    public void getAllByIsbnShow(String isbn) {
        this.isbn = isbn;
    }

    //Search database by Nama_Library
    public ArrayList<LibraryRDF> getSearchRDFLibrary() {
        return new SearchLibraryRDFDAO().SearchRDFLibrary(search);
    }

}
