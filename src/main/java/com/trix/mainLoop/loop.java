package com.trix.mainLoop;

import java.io.IOException;
import java.util.Scanner;

import com.trix.model.AuthenticationTmp;
import com.trix.serverAccess.SAInterface;
import com.trix.serverAccess.SAInterfaceImpl;

public class loop {

	private String login;
	private String haslo;
	private boolean loginFlag = true;
	private Scanner scanner = new Scanner(System.in);
	private SAInterface dataAccess = new SAInterfaceImpl();
	
	public void mainApplicationLoop() throws IOException{
		
		while(loginFlag) {
			
			System.out.println("Podaj login: ");
			login = scanner.next();
			System.out.println("Podaj haslo: ");
			haslo = scanner.next();
			
			AuthenticationTmp at = dataAccess.login(login, haslo);
			
			this.loginFlag = at.isLoginFlag();
		}
		
	}
}
