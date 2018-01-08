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
		ReturnValueModel returnValue = setReturnValueModel(query);
		System.out.println(gson.toJson(returnValue));
		result.setReturnValue(returnValue);
		return result;
	}
	
	public ReturnValueModel setReturnValueModel(TaskQuery query) {
		
		ReturnValueModel returnValue = new ReturnValueModel();
		List<String> singerLocation = playlistRead("./songId/test.txt");
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
			try {
				String result = getSearch.getSearch(singerName + "+" + songName);
				//returnValue.setReply(result + "你要聽哪一首");
				returnValue.setReply("你要聽的歌曲為 : " + singerName + "的" + songName);
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
				try {
					String result = getSearch.getSearch(singerName + "+" + songName);
					//returnValue.setReply(result + "你要聽哪一首");
					returnValue.setReply("你要聽的歌曲為 : " + singerName + "的" + songName);
					returnValue.setResultType("CONFIRM");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
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
			String listPath = "./playlist/"+ playlist_name +".txt";
			File file = new File(listPath);
			returnValue.setReply("建立"+playlist_name+"成功");
			returnValue.setResultType("CONFIRM");
			break;
		
		case "add_song" :
			playlist_name = query.getSlotEntities().get(0).getOriginalValue();
			singerName = query.getSlotEntities().get(1).getOriginalValue();
			String singerId = "";
			songName = query.getSlotEntities().get(2).getOriginalValue();
			String song_id="";
			
			for(String location : singerLocation) {
				if(location.contains(singerName))
					singerId = location.split("\t")[1];
			}
			listPath = "./playlist/"+ playlist_name +".txt";
			String singerPath = "./songId/"+singerId+".txt";
			
			List<String> playList = playlistRead(listPath);
			List<String> songList = playlistRead(singerPath);
			
			for(String songInfo : songList) {
				if(songInfo.contains(songName)){
					song_id = songInfo.split("\t")[1];
					playList.add(song_id);
					}
			}
			
			PlaylistWrite(listPath,playList);
			
			returnValue.setReply("加入"+playlist_name+"成功");
			returnValue.setResultType("RESULT");
			break;
			
		case "play_playlist" :
			playlist_name = query.getSlotEntities().get(0).getOriginalValue();
			listPath = "./playlist/"+ playlist_name +".txt";
			
				List<String> songs = playlistRead(listPath);
				
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
			break;
		
		case "start_play" :
			if(query.getSlotEntities().size()>1) {
				
				singerName = query.getSlotEntities().get(1).getOriginalValue();
				songName = query.getSlotEntities().get(2).getOriginalValue();
				singerId = "";
				for(String location : singerLocation) {
					if(location.contains(singerName))
						singerId = location.split("\t")[1];
				}
				singerPath = "./songId/"+singerId+".txt";
					songs = playlistRead(singerPath);

					for(int i=0;i<songs.size();i++) {
						if(songs.get(i).contains(songName)){
							song_id = songs.get(i).split("\t")[1];
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

				
				
			}else {
				returnValue.setReply("還沒搜尋歌曲 請問要聽哪首歌");
				returnValue.setResultType("RESULT");
			}
			break;
		
		case "list_playlist" :
			
			playlist_name = query.getSlotEntities().get(0).getOriginalValue();
			listPath = "./playlist/"+ playlist_name +".txt";
			
			songs = playlistRead(listPath);
			String songsList="";
			for(String song_info : songs) {
				songsList = songsList + song_info + "  ";
			}
			
			returnValue.setReply(songsList);
			returnValue.setResultType("CONFIRM");
			break;
		default :
			returnValue.setReply("------架構中------");
			returnValue.setResultType("CONFIRM");
		}
		
		
		return returnValue;
	}
	
	public List<String> playlistRead(String path){
		
		BufferedReader br = null;
		FileReader fr = null;
		List<String> readList = new ArrayList<>();

		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);

			String sCurrentLine;					
			while ((sCurrentLine = br.readLine()) != null) {
				readList.add(sCurrentLine);
			}
		} catch (IOException e) {

			e.printStackTrace();
		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}	
		
		return readList;
	}
	
	public void PlaylistWrite(String path , List<String> songList) {
		
		try {
            // Assume default encoding.
            FileWriter fileWriter =
                new FileWriter(path);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
           for(String song : songList) {
            bufferedWriter.write(song);
            bufferedWriter.newLine();
           }

            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + path + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
	}
}
