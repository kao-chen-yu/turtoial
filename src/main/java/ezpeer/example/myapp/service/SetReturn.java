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
			String songName = "";
			String singerName = "";
			System.out.println("-------ya it is search_song--------");
			if("song_name".equals(query.getSlotEntities().get(0).getIntentParameterName())) {
				songName = query.getSlotEntities().get(0).getOriginalValue();
				singerName = query.getSlotEntities().get(1).getOriginalValue();
			}else {
				singerName = query.getSlotEntities().get(0).getOriginalValue();
				songName = query.getSlotEntities().get(1).getOriginalValue();	
			}
			System.out.println(songName);
			returnValue.setReply("你要聽的歌曲為 :" + singerName + "的" + songName);
			break;
		case "call_MyMusic" :
			System.out.println("-------wake up MyMusic--------");
			returnValue.setReply(" MyMusic 服務啟動...請問要聽哪首歌? ");
			break;
		default :
			returnValue.setReply("------架構中------");
		}
		
		returnValue.setResultType("RESULT");
		return returnValue;
	}
	
	
}
