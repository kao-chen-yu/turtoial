package ezpeer.example.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import ezpeer.example.myapp.service.SetReturn;
import ezpeer.example.myapp.vo.ReturnModel;


@Controller
@SpringBootApplication
public class DemoApplication {
	
	@Autowired
	private SetReturn result;
	
	@RequestMapping("/")
    @ResponseBody
    String home() {
      return "Hello World!";
    }
	
	@RequestMapping(value = "/skill/weather", method = RequestMethod.POST)
    public @ResponseBody String getResponse(@RequestBody String taskQuery) {
 
        /**
         * 将开发者平台识别到的语义理解的结果（json字符串格式）转换成TaskQuery
         */
		System.out.println("---------skill ---------------");
		//System.out.println(taskQuery);
		ReturnModel returnResult = result.setReturnModel();
		 Gson gson = new Gson();
		 
		System.out.println(gson.toJson(returnResult));
		return gson.toJson(returnResult);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
