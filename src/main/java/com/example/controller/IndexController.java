package com.example.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class IndexController {
	

	@RequestMapping(value = "/skill/test", method = RequestMethod.GET)
    public @ResponseBody void getResponse(@RequestBody String taskQuery) {
 
        /**
         * 将开发者平台识别到的语义理解的结果（json字符串格式）转换成TaskQuery
         */
		System.out.println("---------skill test---------------");
		//System.out.println(taskQuery);
 
        
    }

}
