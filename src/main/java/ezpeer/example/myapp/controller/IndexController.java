package ezpeer.example.myapp.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import ezpeer.example.myapp.service.GetSearch;
import ezpeer.example.myapp.service.SetReturn;
import ezpeer.example.myapp.vo.ReturnModel;
import ezpeer.example.myapp.vo.TaskQuery;


@RestController
public class IndexController {
	
	@Autowired
	private SetReturn result;
	
	@Autowired
	private GetSearch getSearch;
	
	@RequestMapping("/")
    @ResponseBody
    String home() {
      return "Hello World 2 !";
    }
	
	@RequestMapping(value = "/skill/weather", method = RequestMethod.POST)
    public @ResponseBody String getResponse(@RequestBody String taskQuery) {
		
		 Gson gson = new Gson();
		 
        /**
         * 将开发者平台识别到的语义理解的结果（json字符串格式）转换成TaskQuery
         */
		System.out.println("---------skill ---------------");
		System.out.println(taskQuery);
		TaskQuery query = gson.fromJson(taskQuery, TaskQuery.class);
		System.out.println("---------slot entity ---------------");
		System.out.println(query.getIntentName());
		
		
		ReturnModel returnResult = result.setReturnModel(query);
		
		
		System.out.println(gson.toJson(returnResult));
		return gson.toJson(returnResult);
    }
	
	@RequestMapping(value = "/skill/test", method = RequestMethod.GET)
    public @ResponseBody String getResponseTest() {
 
        /**
         * 将开发者平台识别到的语义理解的结果（json字符串格式）转换成TaskQuery
         */
		Gson gson = new Gson();
		System.out.println("-----------skill test---------------");
		String results = "";
		/*try {
			result =getSearch.getSearch("五月天+倔強");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		http://ezpeer2.herokuapp.com/search/%E4%BA%94%E6%9C%88%E5%A4%A9
		//File file = new File(path);
		try {
			results = getSearch.getSongId("%E4%BA%94%E6%9C%88%E5%A4%A9");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*Path dir = Paths.get("./songId");
		
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir,"*")){
			
			for(Path file : stream) {
				System.out.println(file.getFileName());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return results;
    }

}
