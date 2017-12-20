package ezpeer.example.myapp.service;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import ezpeer.example.myapp.vo.ReturnModel;
import ezpeer.example.myapp.vo.ReturnValueModel;
import ezpeer.example.myapp.vo.TaskQuery;

@Service
public class SetReturn {
	
	
	public ReturnModel setReturnModel(TaskQuery query) {
		
		ReturnModel result = new ReturnModel();
		
		result.setReturnCode("0");
		result.setReturnErrorSolution(" ");
		result.setReturnMessage("Success");
		 Gson gson = new Gson();
		ReturnValueModel returnValue = setReturnValueModel(query);
		System.out.println(gson.toJson(returnValue));
		result.setReturnValue(returnValue);
		return result;
	}
	
	public ReturnValueModel setReturnValueModel(TaskQuery query) {
		
		ReturnValueModel returnValue = new ReturnValueModel();
		
		switch(query.getIntentName()) {
		
		case "search_song" :
			System.out.println("-------ya it is search_song--------");
			String songName = query.getSlotEntities().get(0).getIntentParameterName();
			String singerName = query.getSlotEntities().get(1).getIntentParameterName();
			returnValue.setReply("你要聽的歌曲為 :"+ singerName + "的" + songName);
			break;
		default :
			returnValue.setReply("------架構中------");
		}
		
		returnValue.setResultType("RESULT");
		return returnValue;
	}
	
	
}
