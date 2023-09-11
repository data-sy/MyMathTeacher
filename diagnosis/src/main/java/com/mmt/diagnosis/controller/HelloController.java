package com.mmt.diagnosis.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hello")
public class HelloController {


    @GetMapping("")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("hello");
    }

    @GetMapping("/image")
    public ResponseEntity<String> imageTest(){
        return ResponseEntity.ok("<a href=\"https://ibb.co/WD0RmTC\"><img src=\"https://i.ibb.co/KND8T1n/dog.jpg\" alt=\"dog\" border=\"0\" /></a>");
    }


}
