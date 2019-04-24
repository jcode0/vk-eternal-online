package ru.jcode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class API {
	
	private final String token;
	private final JsonParser JsonParser = new JsonParser();
	private final String VKAPI_VERSION = "5.90";
	
	public API(String token) {
		this.token = token;
	}
	
	public boolean setOnlineStatus() {
		JsonObject jo = parseJsonObject(getRequestVKAPI("account.setOnline?"));
		
		if(jo.has("error")) {
			jo = jo.get("error").getAsJsonObject();
			System.out.println("Ошибка >> " + jo.get("error_msg").getAsString());
		}else if(jo.has("response")) {			
			return jo.get("response").getAsInt() == 1;
		}		
		
		return false;
	}
	
	public boolean isValidToken() {
		JsonObject jo = parseJsonObject(getRequestVKAPI("users.get?"));
		
		if(jo.has("error")) {
			jo = jo.get("error").getAsJsonObject();
			System.out.println("Ошибка >> " + jo.get("error_msg").getAsString());
		}else if(jo.has("response")) {
			jo = jo.get("response").getAsJsonArray().get(0).getAsJsonObject();
			System.out.println("Токен действителен для "+jo.get("first_name").getAsString()+" "+jo.get("last_name").getAsString());
			return true;
		}
		
		return false;
	}
	
	public JsonObject parseJsonObject(String line) {
		return JsonParser.parse(line).getAsJsonObject();
	}
	
    public String getRequest(String url) {
		try {
	        URLConnection connection = new URL(url.replace(" ", "%20")).openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	
	        StringBuilder response = new StringBuilder();
	        String inputLine;
	
	        while((inputLine = in.readLine()) != null) {
	            response.append(inputLine);
	        }
	
	        in.close();
	        return response.toString();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
        return "";
    }

    public String getRequestVKAPI(String method_name) {
    	return getRequest("https://api.vk.com/method/"+method_name+"&v="+VKAPI_VERSION+"&access_token="+token);
    }

	public String getTime() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE);
	}    
}	