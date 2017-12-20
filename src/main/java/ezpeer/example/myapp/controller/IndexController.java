package ezpeer.example.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import ezpeer.example.myapp.service.SetReturn;
import ezpeer.example.myapp.vo.ReturnModel;
import ezpeer.example.myapp.vo.TaskQuery;


@RestController
public class IndexController {
	
	@Autowired
	private SetReturn result;
	
	@RequestMapping("/")
    @ResponseBody
    String home() {
      return "Hello World!";
    }
	
	@RequestMapping(value = "/skill/weather", method = RequestMethod.POST)
    public @ResponseBody String getResponse(@RequestBody String taskQuery) {
		
		 Gson gson = new Gson();
		 
        /**
         * 将开发者平台识别到的语义理解的结果（json字符串格式）转换成TaskQuery
         */
		System.out.println("---------skill ---------------");
		//System.out.println(taskQuery);
		TaskQuery query = gson.fromJson(taskQuery, TaskQuery.class);
		System.out.println(query.getIntentName());
		ReturnModel returnResult = result.setReturnModel();
		
		
		System.out.println(gson.toJson(returnResult));
		return gson.toJson(returnResult);
    }
	
	@RequestMapping(value = "/skill/test", method = RequestMethod.GET)
    public @ResponseBody void getResponseTest(@RequestBody String taskQuery) {
 
        /**
         * 将开发者平台识别到的语义理解的结果（json字符串格式）转换成TaskQuery
         */
		System.out.println("---------skill test---------------");
		//System.out.println(taskQuery);
 
        
    }

}
