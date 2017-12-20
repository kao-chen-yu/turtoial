package com.example.myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@SpringBootApplication
public class DemoApplication {
	
	@RequestMapping("/")
    @ResponseBody
    String home() {
      return "Hello World!";
    }
	
	@RequestMapping(value = "/skill/weather", method = RequestMethod.POST)
    public @ResponseBody void getResponse(@RequestBody String taskQuery) {
 
        /**
         * 将开发者平台识别到的语义理解的结果（json字符串格式）转换成TaskQuery
         */
		System.out.println("---------skill ---------------");
		System.out.println(taskQuery);
 
        
    }
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
