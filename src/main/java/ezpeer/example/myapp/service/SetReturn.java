package ezpeer.example.myapp.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import ezpeer.example.myapp.vo.ActionModel;
import ezpeer.example.myapp.vo.ReturnModel;
import ezpeer.example.myapp.vo.ReturnValueModel;
import ezpeer.example.myapp.vo.TaskQuery;

@Service
public class SetReturn {
	
	@Autowired
	private GetSearch getSearch;	
	
	public ReturnModel setReturnModel(TaskQuery query) {
		
		ReturnModel result = new ReturnModel();
		
		result.setReturnCode("0");
		result.setReturnErrorSolution(" ");
		result.setReturnMessage("Success");
		 Gson gson = new Gson();
		ReturnValueModel returnValue;
		try {
			returnValue = setReturnValueModel(query);
			System.out.println(gson.toJson(returnValue));
			result.setReturnValue(returnValue);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public ReturnValueModel setReturnValueModel(TaskQuery query) throws Exception {
		
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
			songName = query.getSlotEntities().get(0).getOriginalValue();
			System.out.println(songName);
			try {
				String result = getSearch.getSearch(songName);
				//returnValue.setReply(result + "你要聽哪一首");
				returnValue.setReply("你要聽的歌曲為 : " + songName);
				returnValue.setResultType("CONFIRM");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
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
				
					String result = getSearch.getSearch(singerName + "+" + songName);
					//returnValue.setReply(result + "你要聽哪一首");
					returnValue.setReply("你要聽的歌曲為 : " + singerName + "的" + songName);
					returnValue.setResultType("CONFIRM");
				
				
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
			String playlistName = query.getSlotEntities().get(0).getOriginalValue();
			
			String result = getSearch.createPlaylist(playlistName);			
			returnValue.setReply(result);
			returnValue.setResultType("CONFIRM");			
			break;
		
		case "add_song" :
			playlistName = query.getSlotEntities().get(0).getOriginalValue();
			singerName = query.getSlotEntities().get(1).getOriginalValue();
			songName = query.getSlotEntities().get(2).getOriginalValue();

			String songInfo = singerName + "\t" + songName;
			
			result = getSearch.addSong(playlistName, songInfo);
			returnValue.setReply(result);
			returnValue.setResultType("RESULT");
			break;
			
		case "play_playlist" :
			playlistName = query.getSlotEntities().get(0).getOriginalValue();			
			singerName = "五月天";	
			String[]  listInfo= getSearch.listPlaylist(playlistName).split("\n");
			List<String> songs = getSearch.getSongId(singerName);
			  
				for(int i=0;i<listInfo.length;i++) {
				for(int j=0;j<songs.size();j++) {
					System.out.println(songs.get(j));
					
					if(songs.get(j).contains(listInfo[i])) {
						song = new HashMap<>();
						action = new ActionModel();
						song.put("audioGenieId",songs.get(j).split("\t")[2]);
						action.setProperties(song);
						actions.add(action);
					}
				}
				}
				returnValue.setActions(actions);
				returnValue.setReply("開始撥放 playlist");
				returnValue.setResultType("RESULT");
			break;
		
		case "start_play" :
			if(query.getSlotEntities().size()>1) {
				
				singerName = query.getSlotEntities().get(1).getOriginalValue();
				songName = query.getSlotEntities().get(2).getOriginalValue();
				String song_id = "";

				try {
					songs = getSearch.getSongId(singerName);
				
					for(int i=0;i<songs.size();i++) {
						if(songs.get(i).contains(songName)){
							song_id = songs.get(i).split("\t")[2];
							Map<String,String> song1 = new HashMap<>();
							ActionModel action1 = new ActionModel();
							
							song1.put("audioGenieId",song_id);
							action1.setProperties(song1);
							actions.add(action1);
							returnValue.setActions(actions);
							returnValue.setReply("開始撥放 start_play");
							returnValue.setResultType("RESULT");
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}else {
				returnValue.setReply("還沒搜尋歌曲 請問要聽哪首歌");
				returnValue.setResultType("RESULT");
			}
			break;
		
		case "list_playlist" :
			
			playlistName = query.getSlotEntities().get(0).getOriginalValue();
			
			String songList = getSearch.listPlaylist(playlistName);
			
			returnValue.setReply(songList);
			returnValue.setResultType("CONFIRM");
			break;
			
		case "delete_playlist" :
			playlistName = query.getSlotEntities().get(0).getOriginalValue();
			
			songList = getSearch.deletePlaylist(playlistName);
			
			returnValue.setReply(songList);
			returnValue.setResultType("RESULT");
			
			break;
		default :
			returnValue.setReply("------架構中------");
			returnValue.setResultType("CONFIRM");
		}
		
		
		return returnValue;
	}
	
	
}
