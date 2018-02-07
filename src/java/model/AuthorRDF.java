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
public class AuthorRDF {

    private RDFNode nama_author;

    public AuthorRDF(RDFNode nama_author) {
        this.nama_author = nama_author;
    }

    public RDFNode getNama_author() {
        return nama_author;
    }

    public void setNama_author(RDFNode nama_author) {
        this.nama_author = nama_author;
    }

}
