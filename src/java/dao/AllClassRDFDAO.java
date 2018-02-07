/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.query.*;

import java.io.*;
import java.util.ArrayList;
import model.AllClassRDF;

/**
 *
 * @author AnsaKhitara
 */
public class AllClassRDFDAO extends Object {

   public ArrayList<AllClassRDF> getAllAllClassRDF(String querySearch) {
        ArrayList<AllClassRDF> AllClassList = new ArrayList();

        /**
         * NOTE that the file is loaded from the class-path and so requires that
         * the data-directory, as well as the directory containing the compiled
         * class, must be added to the class-path when running this and
         * subsequent examples.
         */
        String inputFileName = "D:/java/S/Digilib_Semantic_Web_Ontology/src/java/data/Search_Digilib_Semantic_Web.rdf";

//    public static void main(String args[]) throws IOException {
        // create an empty model
        Model model = ModelFactory.createDefaultModel();
        InputStream in = FileManager.get().open(inputFileName);
        if (in == null) {
            throw new IllegalArgumentException("File: " + inputFileName + " not found");
        }

        // read the AllClassRDFDAO/XML file
        model.read(in, null);
        String queryString
                = "PREFIX vocab: <http://localhost:2020/vocab/>\n"
                + "PREFIX article: <http://localhost:2020/article/>\n"
                + "PREFIX library: <http://localhost:2020/library/>\n"
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "SELECT DISTINCT ?property ?hasValue ?isValueOf\n"
                + "WHERE {\n"
                + "  { <"+querySearch+"> ?property ?hasValue }\n"
                + "  UNION\n"
                + "  { ?isValueOf ?property <"+querySearch+"> }\n"
                + "}\n"
                + "ORDER BY (!BOUND(?hasValue)) ?property ?hasValue ?isValueOf";

        Query query = QueryFactory.create(queryString);

        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet rs = qe.execSelect();
        
        while (rs.hasNext()) {
                QuerySolution qs = rs.next();
                RDFNode property = qs.get("property");
                RDFNode hasValue = qs.get("hasValue");
                RDFNode isValueOf = qs.get("isValueOf");
                AllClassRDF Ar = new AllClassRDF(property,hasValue,isValueOf);
                AllClassList.add(Ar);
            }

        // Important - free up resources used running the query
        qe.close();
        return AllClassList;
    }
   
}
