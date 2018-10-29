package com.gae.searchApi.service.impl;

import com.gae.searchApi.Model.Contact;
import com.gae.searchApi.service.SearchService;
import com.google.appengine.api.search.*;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class SearchServiceImp implements SearchService {

    private String name = "default";

    private Index getIndex(String indexName) {
        IndexSpec indexSpec = IndexSpec.newBuilder().setName(indexName).build();
        Index index = SearchServiceFactory.getSearchService().getIndex(indexSpec);
        return index;
    }

    public void createDocs() {
        Index index = getIndex(name);
        for (int j = 0; j < 500; j++) {
            List<Document> docs = new ArrayList<Document>();
            for (int i = 0; i < 200; i++) {
                docs.add(create(i, j));
            }

            index.putAsync(docs);
        }
        System.out.println("Completed");
    }

    public void addDoc(Contact contact, String docId) {
        Index index = getIndex(name);
        index.putAsync(createDoc(contact, docId));
    }

    private Document createDoc(Contact contact, String docId) {
        Document doc = Document.newBuilder().setId("doc_id_" + docId)
                .addField(Field.newBuilder().setName("FirstName").setText(contact.getFirstName()))
                .addField(Field.newBuilder().setName("Address_1").setText(contact.getAddress1()))
                .addField(Field.newBuilder().setName("Address_2").setText(contact.getAddress2()))
                .addField(Field.newBuilder().setName("City").setText(contact.getCity()))
                .addField(Field.newBuilder().setName("Business").setText(contact.getBusiness()))
                .addField(Field.newBuilder().setName("Email").setText(contact.getEmail()))
                .addField(Field.newBuilder().setName("LastName").setText(contact.getLastName()))
                .addField(Field.newBuilder().setName("Number").setNumber(contact.getNumber()))
                .addField(Field.newBuilder().setName("Zip").setNumber(contact.getZip()))
                .addField(Field.newBuilder().setName("Created").setDate(new Date()))
                .build();

        return doc;

    }

    private Document create(int i, int j) {
        DataFactory df = new DataFactory();

        Document doc = Document.newBuilder().setId("doc_id_" + String.valueOf(j) + "_" + String.valueOf(i))
                .addField(Field.newBuilder().setName("FirstName").setText(df.getFirstName()))
                .addField(Field.newBuilder().setName("Address_1").setText(df.getAddress()))
                .addField(Field.newBuilder().setName("Address_2").setText(df.getAddressLine2()))
                .addField(Field.newBuilder().setName("City").setText(df.getCity()))
                .addField(Field.newBuilder().setName("Business").setText(df.getBusinessName()))
                .addField(Field.newBuilder().setName("Email").setText(df.getEmailAddress()))
                .addField(Field.newBuilder().setName("LastName").setText(df.getLastName()))
                .addField(Field.newBuilder().setName("Number").setNumber(df.getNumberBetween(23456, 99999)))
                .addField(Field.newBuilder().setName("Zip").setNumber(df.getNumberBetween(500000, 899999)))
                .addField(Field.newBuilder().setName("Created").setDate(new Date()))
                .build();

        return doc;
    }

    public Results<ScoredDocument> getResults(String queryString) {
        Query query = Query.newBuilder().build(queryString);
        return getIndex(name).search(query);
    }
}
