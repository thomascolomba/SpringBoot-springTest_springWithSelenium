package com.thomas.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/showMessage")
public class HelloController {
	
  @RequestMapping(method = RequestMethod.GET)
  public String sendHelloMessage() {
    return "Hello from java layer";
  }
}