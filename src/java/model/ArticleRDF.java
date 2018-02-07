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
public class ArticleRDF {

    public RDFNode title;

    public ArticleRDF(RDFNode title) {
        this.title = title;
    }

    public RDFNode getTitle() {
        return title;
    }

    public void setTitle(RDFNode title) {
        this.title = title;
    }

}
