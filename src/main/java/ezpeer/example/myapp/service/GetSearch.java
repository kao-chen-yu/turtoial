package ezpeer.example.myapp.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Service
public class GetSearch {

	public String getSearch(String searchInfo) throws Exception{
		
		String url = "http://search.mymusic.net.tw/mobile/index?select4=ftsong&pageNo=1&pageSize=3&out_type=json&textfield2="+searchInfo;

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
}
