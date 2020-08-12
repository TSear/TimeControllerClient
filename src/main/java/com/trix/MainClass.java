package com.trix;

import java.io.IOException;

import com.trix.mainLoop.loop;
import com.trix.serverAccess.SAInterface;
import com.trix.serverAccess.SAInterfaceImpl;

public class MainClass {

	public static void main(String[] args) throws IOException{
		
		loop mainLoop = new loop();
		mainLoop.mainApplicationLoop();
		
	}
	
}
