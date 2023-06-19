package com.example.joblisting.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/example")
public class PostController {

  @GetMapping
  public String hello() {
    return "Hello world!";
  }
}