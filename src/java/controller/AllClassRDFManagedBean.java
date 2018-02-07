/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AllClassRDFDAO;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.AllClassRDF;
import org.apache.jena.rdf.model.RDFNode;

/**
 *
 * @author AnsaKhitara
 */
@ManagedBean
@SessionScoped
public class AllClassRDFManagedBean {
    private String property;
    private String hasValue;
    private String isValueOf;
    private String search;

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getHasValue() {
        return hasValue;
    }

    public void setHasValue(String hasValue) {
        this.hasValue = hasValue;
    }

    public String getIsValueOf() {
        return isValueOf;
    }

    public void setIsValueOf(String isValueOf) {
        this.isValueOf = isValueOf;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    //Show all
    public ArrayList<AllClassRDF> getAllClassRDFShow() {
        String query = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get( "query" );
        return new AllClassRDFDAO().getAllAllClassRDF(query);
    }

    //Show by id
    public void getAllByClassShow(String property, String hasValue, String isValueOf) {
        this.property = property;
        this.hasValue = hasValue;
        this.isValueOf = isValueOf;
    }

    //Reset setter null;
    public void resetValue() {
        //Reset update
        this.property = "";
        this.hasValue = "";
        this.isValueOf = "";
    }

    //Search database by Title
//    public ArrayList<AllClassRDF> getSearchRDFAllClass() {
//        return new AllClassSearchRDFDAO().SearchRDFAllClass(search);
//    }
    
    public boolean isURIResource(RDFNode node){
        return node.isURIResource();
//        return false;
    }
}
