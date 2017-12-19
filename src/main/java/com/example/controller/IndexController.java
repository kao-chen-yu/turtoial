package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class IndexController {
	
	@RequestMapping(value = "/test")
	public String home() {
	
		System.out.println("-----------------------------------------------------");
      return "Hello World!";
    }

}
