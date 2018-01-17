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
	
	@RequestMapping(value = "/skill/weather2", method = RequestMethod.POST)
    public @ResponseBody String getResponse2(@RequestBody String task) {
		
		 Gson gson = new Gson();
		 
        /**
         * 将开发者平台识别到的语义理解的结果（json字符串格式）转换成TaskQuery
         */
		System.out.println("task is :" + task);
		return "weather2 ! ";
    }
	@RequestMapping(value = "/skill/test", method = RequestMethod.GET)
    public @ResponseBody String getResponseTest() {
 
        /**
         * 将开发者平台识别到的语义理解的结果（json字符串格式）转换成TaskQuery
         */
		
		System.out.println("-----------skill test---------------");
		String results = "--架構--";
		
		try {
			//results = getSearch.createPlaylist("高震育");
			result.TestForIfttt();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
    }

	@RequestMapping(value = "/skill/test1", method = RequestMethod.GET)
    public @ResponseBody String getResponseTest123() {
 
        /**
         * 将开发者平台识别到的语义理解的结果（json字符串格式）转换成TaskQuery
         */
		
		System.out.println("-----------skill test---------------");
		String results = "--架構--";
		
		try {
			results = getSearch.listPlaylistName();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
    }
	
	@RequestMapping(value = "/skill/test2", method = RequestMethod.GET)
    public @ResponseBody String getResponseTest2() {
 
        /**
         * 将开发者平台识别到的语义理解的结果（json字符串格式）转换成TaskQuery
         */
		
		System.out.println("-----------skill test---------------");
		String results = "--架構--";
		
		try {
			results = getSearch.addSong("高震育", "五月天 一顆蘋果");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
    }
	
	@RequestMapping(value = "/skill/test3", method = RequestMethod.GET)
    public @ResponseBody String getResponseTest3() {
 
        /**
         * 将开发者平台识别到的语义理解的结果（json字符串格式）转换成TaskQuery
         */
		
		System.out.println("-----------skill test---------------");
		String results = "--架構--";
		
		try {
			String[]  listInfo = getSearch.listPlaylist("我的最爱").split("\n");
			System.out.println(listInfo[0]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
    }
	
	@RequestMapping(value = "/skill/test4", method = RequestMethod.GET)
    public @ResponseBody String getResponseTest4() {
 
        /**
         * 将开发者平台识别到的语义理解的结果（json字符串格式）转换成TaskQuery
         */
		
		System.out.println("-----------skill test---------------");
		String results = "--架構--";
		
		try {
			results = getSearch.deletePlaylist("高震育");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
    }
	
	@RequestMapping(value = "/skill/test5", method = RequestMethod.GET)
    public @ResponseBody String getResponseTest5() {
 
        /**
         * 将开发者平台识别到的语义理解的结果（json字符串格式）转换成TaskQuery
         */
		
		System.out.println("-----------skill test---------------");
		String results = "--架構--";
		
		try {
			results = getSearch.addSong("高震育", "五月天 2012");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
    }
}
