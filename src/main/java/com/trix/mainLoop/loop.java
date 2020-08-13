package com.trix.mainLoop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
	private SAInterface dataAccess = new SAInterfaceImpl();
	private WindowNameReader wnr = new WindowNameReader();
	private ApplicationWrapper appWrapper = new ApplicationWrapper();
	private int cycle = 0;
	private static int cycleLenght = 3;
	
	public void mainApplicationLoop() throws Exception{
		
		while(!authTmp.isLoginFlag()) {
			
			System.out.println("Podaj login: ");
			login = scanner.next();
			System.out.println("Podaj haslo: ");
			haslo = scanner.next();
			
			AuthenticationTmp at = dataAccess.login(login, haslo);
			authTmp.setId(at.getId());
			authTmp.setLoginFlag(at.isLoginFlag());
		}
		
		while(true) {	
			Thread.sleep(cycleLenght*1000);
			appWrapper.getApplicationList().add(new ApplicationModel(wnr.read(),Long.parseLong(cycleLenght+""),authTmp.getId()));
			cycle++;
			if(cycle == 2) {
				dataAccess.save(appWrapper);
				cycle=0;
			}
		}
		
	}
}
