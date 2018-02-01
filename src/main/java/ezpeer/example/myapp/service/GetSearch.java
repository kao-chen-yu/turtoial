package ezpeer.example.myapp.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Service
public class GetSearch {

	public String getSearch(String searchInfo) throws Exception{
		
		System.out.println("這是資訊 :" + searchInfo);
		String encodedSearch = URLEncoder.encode(searchInfo, "UTF-8");
		String url = "http://search.mymusic.net.tw/mobile/index?select4=ftsong&pageNo=1&pageSize=3&out_type=json&textfield2="+encodedSearch;

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		String searchResult=" ";
		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
		
		Gson gson = new Gson();
		JsonElement jelem = gson.fromJson(response.toString(), JsonElement.class);
		JsonObject searchJson = jelem.getAsJsonObject();
		int songTotal = searchJson.get("total").getAsInt();
		JsonArray songInfo = searchJson.get("song").getAsJsonArray();
		
		for(int i=0;i<songInfo.size();i++){
		String singer =songInfo.get(0).getAsJsonObject().get("singer_name").getAsString();
		String song =songInfo.get(0).getAsJsonObject().get("song_name").getAsString();
		searchResult = searchResult +(i+1) + " " + singer + " \t " +  song + " \n ";
		}
		return searchResult;
	}
	
	public List<String> getSongId(String singerName) throws Exception{
	
		String encodedSinger = URLEncoder.encode(singerName, "UTF-8");
		String url = "http://ezpeer2.herokuapp.com/search/"+encodedSinger;
			
		System.out.println(url);
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		String searchResult=" ";
		List<String> songInfo = new ArrayList<>();  
		// optional default is GET
		con.setRequestMethod("GET");
		
		//add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
			songInfo.add(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
		System.out.println(songInfo.size());
		
		return songInfo;
	}
	
	public String createPlaylist(String playlistName) throws Exception{
		
		String encodePlaylist = URLEncoder.encode(playlistName, "UTF-8");
		String url = "http://ezpeer2.herokuapp.com/createplaylist/" + encodePlaylist;
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
		
		//add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
		
		return response.toString();
	}
	
	public String deletePlaylist(String playlistName) throws Exception{
		
		String encodePlaylist = URLEncoder.encode(playlistName, "UTF-8");
		String url = "http://ezpeer2.herokuapp.com/deleteplaylist/" + encodePlaylist;
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
		
		//add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
		
		return response.toString();
	}
	
	public String addSong(String playlistName,String songInfo) throws Exception{
		
		String encodePlaylist = URLEncoder.encode(playlistName, "UTF-8");
		String encodeSongInfo = URLEncoder.encode(songInfo, "UTF-8");
		String url = "http://ezpeer2.herokuapp.com/addsong/" + encodePlaylist + "/" + encodeSongInfo;
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
		
		//add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
		
		return response.toString();
	}
	
	public String listPlaylist(String playlistName) throws Exception{
		
		String encodePlaylist = URLEncoder.encode(playlistName, "UTF-8");
		String url = "http://ezpeer2.herokuapp.com/listplaylist/" + encodePlaylist;
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
		
		//add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		
		Stream<String> strs = in.lines();
		return strs.collect(Collectors.joining("\n"));
	}
	
	public String listPlaylistName() throws Exception{
		
		
		String url = "http://ezpeer2.herokuapp.com/listPlaylistname/" ;
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
		
		//add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
		
		return response.toString();
	}
	
	
}
