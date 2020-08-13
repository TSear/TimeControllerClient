package com.trix.serverAccess;

import java.util.regex.Pattern;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.RECT;

public class WindowNameReader {

	   private static final int MAX_TITLE_LENGTH = 1024;

	    public String read() throws Exception {
	        char[] buffer = new char[MAX_TITLE_LENGTH * 2];
	        HWND hwnd = User32.INSTANCE.GetForegroundWindow();
	        User32.INSTANCE.GetWindowText(hwnd, buffer, MAX_TITLE_LENGTH);
	        String buffered = new String(buffer);
	        String[] splited = buffered.split("(-|–)");
	        String s = splited[splited.length-1];
	        return s.trim();
	    }
	
}
