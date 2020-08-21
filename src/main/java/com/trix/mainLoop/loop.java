package com.trix.mainLoop;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.lucene.queryparser.classic.CharStream;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.classic.QueryParserBase;
import org.apache.lucene.queryparser.classic.QueryParserTokenManager;
import org.apache.lucene.search.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trix.model.ApplicationModel;
import com.trix.model.ApplicationWrapper;
import com.trix.model.AuthenticationTmp;
import com.trix.serverAccess.SAInterface;
import com.trix.serverAccess.SAInterfaceImpl;
import com.trix.serverAccess.WindowNameReader;

public class loop {

	private String 	login;
	private String 	haslo;
	private AuthenticationTmp authTmp = new AuthenticationTmp();
	private Scanner scanner = new Scanner(System.in);
	private SAInterface dataAccess = new SAInterfaceImpl() ;
	private WindowNameReader wnr = new WindowNameReader();
	private ApplicationWrapper appWrapper = new ApplicationWrapper();
	private int cycle = 0;
	private static int cycleLenght = 3;

	
	public void mainApplicationLoop() throws Exception{
		
		while(!authTmp.isLoginFlag()) {
			System.out.println("Podaj login: ");
			login = Pattern.quote(scanner.next());
			login = login.substring(2,login.length()-2);
			System.out.println("Podaj haslo: ");
			haslo = Pattern.quote(scanner.next());
			haslo = haslo.substring(2, haslo.length()-2);
			System.out.println(login);
			
			AuthenticationTmp at = dataAccess.login(login, haslo);
			authTmp.setId(at.getId());
			authTmp.setLoginFlag(at.isLoginFlag());
		}
		
		while(true) {	
			Thread.sleep(cycleLenght*1000);
			appWrapper.getApplicationList().add(new ApplicationModel(wnr.read(),Long.parseLong(cycleLenght+""),authTmp.getId()));
			System.out.println(LocalTime.now());
			cycle++;
			if(cycle == 2) {
				dataAccess.save(appWrapper);
				appWrapper.getApplicationList().clear();
				cycle=0;
			}
		}
		
	}
}
