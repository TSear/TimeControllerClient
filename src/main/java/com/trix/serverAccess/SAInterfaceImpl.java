package com.trix.serverAccess;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.transform.Source;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.trix.model.ApplicationModel;
import com.trix.model.ApplicationWrapper;
import com.trix.model.AuthenticationTmp;

import okhttp3.MediaType;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SAInterfaceImpl implements SAInterface {

	
	private OkHttpClient client = new OkHttpClient();
	private ObjectMapper om = new ObjectMapper();
	private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
	private AuthenticationTmp auth = new AuthenticationTmp();
	private String token = "";

	@Override
	public List<ApplicationModel> getAllData(Long id) throws IOException{
		
		Request rq = new Request.Builder()
				.url("http://localhost:8080/api/application/account/"+id)
				.header("Authorization", this.token)
				.build();
		
			Response response = client.newCall(rq).execute();
			ApplicationModel[] am = om.readValue(response.body().string(), ApplicationModel[].class);
			List<ApplicationModel> applicationList = new ArrayList<>(Arrays.asList(am));
			
			applicationList.stream().forEach(System.out::println);
			
		return applicationList;
	}

	@Override
	public AuthenticationTmp login(String login, String haslo) throws IOException{
		
		JsonObject loginData = new JsonObject();
		loginData.addProperty("username", login);
		loginData.addProperty("password", haslo);
		
		RequestBody rb = RequestBody.create(new Gson().toJson(loginData), JSON);
		Request request = new Request.Builder().url("http://localhost:8080/api/account/login")
				.post(rb)
				.build();
		
		Response response = client.newCall(request).execute();
		AuthenticationTmp at = om.readValue(response.body().string(), AuthenticationTmp.class);
		token = at.getToken();
		return at;
	}

	@Override
	public void save(ApplicationWrapper appWrap) throws Exception{
		
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String jsonToSend = gson.toJson(appWrap);
		RequestBody rb = RequestBody.create(jsonToSend, JSON);
		Request rq = new Request.Builder()
				.url("http://localhost:8080/api/application/new")
				.post(rb)
				.header("Authorization", this.token)
				.build();
		Response response = client.newCall(rq).execute();
		response.close();
	}

}
