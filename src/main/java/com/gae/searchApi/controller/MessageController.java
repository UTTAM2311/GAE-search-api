package com.gae.searchApi.controller;


import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/")
@RestController
@Api(tags = "Message check", description = "")
public class MessageController {

    @GetMapping(produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("I am Healthy !");
    }

}
