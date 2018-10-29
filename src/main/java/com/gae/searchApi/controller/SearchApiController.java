package com.gae.searchApi.controller;

import com.gae.searchApi.Model.Contact;
import com.gae.searchApi.service.SearchService;
import com.google.appengine.api.search.Document;
import com.google.appengine.api.search.Results;
import com.google.appengine.api.search.ScoredDocument;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
@Api(tags = "SearchAPI", description = "GAE SearchApi for quering")
public class SearchApiController {

    @Autowired
    private SearchService service;

    @PostMapping(path = "/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> create() {
        service.createDocs();
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/add", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> add(@RequestBody Contact contact, @RequestParam("docId") String docId) {
        service.addDoc(contact,docId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/search", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Results<ScoredDocument>> search(@RequestParam("query") String query) {
        return ResponseEntity.ok(service.getResults(query));
    }

}
