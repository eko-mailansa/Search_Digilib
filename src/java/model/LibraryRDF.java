/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.apache.jena.rdf.model.RDFNode;

/**
 *
 * @author AnsaKhitara
 */
public class LibraryRDF {

    private RDFNode isbn;

    public LibraryRDF(RDFNode isbn) {
        this.isbn = isbn;
    }

    public RDFNode getIsbn() {
        return isbn;
    }

    public void setIsbn(RDFNode isbn) {
        this.isbn = isbn;
    }

}
