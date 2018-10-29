package com.gae.searchApi.service;

import com.gae.searchApi.Model.Contact;
import com.google.appengine.api.search.Results;
import com.google.appengine.api.search.ScoredDocument;

public interface SearchService {

    void addDoc(Contact contact, String docId);

    void createDocs();

    Results<ScoredDocument> getResults(String queryString);
}
