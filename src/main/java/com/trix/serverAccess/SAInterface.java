package com.trix.serverAccess;

import java.io.IOException;
import java.util.List;

import com.trix.model.ApplicationModel;
import com.trix.model.AuthenticationTmp;

public interface SAInterface {

	public List<ApplicationModel> getAllData(Long id) throws IOException;
	public AuthenticationTmp login(String login, String haslo) throws IOException; 
	
}
