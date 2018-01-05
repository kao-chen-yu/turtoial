package ezpeer.example.myapp.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import ezpeer.example.myapp.vo.ActionModel;
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
		
		List<ActionModel> actions = new ArrayList<>();
		String songName = "";
		String singerName = "";
		
		
		switch(query.getIntentName()) {

		case "call_MyMusic" :
			System.out.println("-------wake up MyMusic--------");
			returnValue.setReply(" My Music 服務啟動...請問要聽哪首歌? ");
			returnValue.setResultType("CONFIRM");
			break;
			
		case "search_song" :
			
			System.out.println("-------ya it is search_song--------");
			if("song_name".equals(query.getSlotEntities().get(0).getIntentParameterName())) {
				songName = query.getSlotEntities().get(0).getOriginalValue();
				singerName = query.getSlotEntities().get(1).getOriginalValue();
			}else {
				singerName = query.getSlotEntities().get(0).getOriginalValue();
				songName = query.getSlotEntities().get(1).getOriginalValue();	
			}
			System.out.println(songName);
			returnValue.setReply("你要聽的歌曲為 : " + singerName + "的" + songName);
			returnValue.setResultType("CONFIRM");
			break;
		
		case "search_singer" :
			if(query.getSlotEntities().size()>1) {
				if("song_name".equals(query.getSlotEntities().get(0).getIntentParameterName())) {
					songName = query.getSlotEntities().get(0).getOriginalValue();
					singerName = query.getSlotEntities().get(1).getOriginalValue();
				}else {
					singerName = query.getSlotEntities().get(0).getOriginalValue();
					songName = query.getSlotEntities().get(1).getOriginalValue();	
				}
				returnValue.setReply("你要聽" + singerName + "的 " + songName);
			}
			else {
				singerName = query.getSlotEntities().get(0).getOriginalValue();
				returnValue.setReply("你要聽" + singerName + "的哪一首歌 ? ");
			}
			returnValue.setResultType("CONFIRM");
			break;
			
		case "test_url" :
			Map<String,String> song = new HashMap<>();
			ActionModel action = new ActionModel();
			song.put("audioGenieId", "947");
			action.setProperties(song);
			actions.add(action);
			song = new HashMap<>();
			action = new ActionModel();
			song.put("audioGenieId", "948");
			action.setProperties(song);
			actions.add(action);
			song = new HashMap<>();
			action = new ActionModel();
			song.put("audioGenieId", "946");
			action.setProperties(song);
			actions.add(action);
			returnValue.setActions(actions);
			returnValue.setReply("開始撥放 一顆蘋果");
			returnValue.setResultType("RESULT");
			break;
			
		case "create_playlist" :
			String playlist_name = query.getSlotEntities().get(0).getOriginalValue();
			Path path = Paths.get("./playlist/"+playlist_name+".txt");
			try {
				Files.createFile(path);			
			returnValue.setReply(playlist_name + "建立成功");
			} catch (IOException e) {
				// TODO Auto-generated catch block
			returnValue.setReply(playlist_name + "建立失敗");
			}
			returnValue.setResultType("CONFIRM");
			break;
		
		case "add_song" :
			playlist_name = query.getSlotEntities().get(0).getOriginalValue();
			singerName = query.getSlotEntities().get(1).getOriginalValue();
			songName = query.getSlotEntities().get(2).getOriginalValue();
			String song_id="";
			
			path = Paths.get("./playlist/test.txt");
			Path singer_path = Paths.get("./songId/test.txt");
			try {
				List<String> songs = Files.readAllLines(singer_path);
				List<String> lines = Files.readAllLines(path);
				for(int i=0;i<songs.size();i++) {
					if(songs.get(i).contains(songName)){
						song_id = songs.get(i).split("\t")[1];
						lines.add(song_id);}
				}
				System.out.println("get song_id" + song_id);
		
			Files.write(path, lines);	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			returnValue.setReply("加入"+playlist_name+"成功");
			returnValue.setResultType("RESULT");
			break;
			
		case "play_playlist" :
			path = Paths.get("./playlist/test.txt");
			try {
				List<String> songs = Files.readAllLines(path);
				
				for(int i=0;i<songs.size();i++) {
					System.out.println(songs.get(i));
					Map<String,String> song1 = new HashMap<>();
					ActionModel action1 = new ActionModel();
					
					song1.put("audioGenieId",songs.get(i));
					action1.setProperties(song1);
					actions.add(action1);
				}
				returnValue.setActions(actions);
				returnValue.setReply("開始撥放 playlist");
				returnValue.setResultType("RESULT");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default :
			returnValue.setReply("------架構中------");
			returnValue.setResultType("CONFIRM");
		}
		
		
		return returnValue;
	}
	
	
}
