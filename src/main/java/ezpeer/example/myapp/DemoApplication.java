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
	

	
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
