package com.util;

import java.util.UUID;

public class StringUtil {
	
	//Éú³Éuuid32
	public static String getUUID32(){
	    String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
	    return uuid;
	//  return UUID.randomUUID().toString().replace("-", "").toLowerCase();
	}
}
