/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.faces.bean.ManagedProperty;
import org.apache.jena.rdf.model.RDFNode;

/**
 *
 * @author AnsaKhitara
 */
public class AllClassRDF {
    private RDFNode property;
    private RDFNode hasValue;
    private RDFNode isValueOf;

    public AllClassRDF(RDFNode property, RDFNode hasValue, RDFNode isValueOf) {
        this.property = property;
        this.hasValue = hasValue;
        this.isValueOf = isValueOf;
    }

    public RDFNode getProperty() {
        return property;
    }

    public void setProperty(RDFNode property) {
        this.property = property;
    }

    public RDFNode getHasValue() {
        return hasValue;
    }

    public void setHasValue(RDFNode hasValue) {
        this.hasValue = hasValue;
    }

    public RDFNode getIsValueOf() {
        return isValueOf;
    }

    public void setIsValueOf(RDFNode isValueOf) {
        this.isValueOf = isValueOf;
    }

}
