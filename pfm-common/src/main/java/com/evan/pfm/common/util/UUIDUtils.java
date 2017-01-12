package com.evan.pfm.common.util;

import java.util.UUID;

public class UUIDUtils {
	/**  
     * 生成32位编码  
     * @return string  
     */    
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString().trim();    
        return uuid;    
    }
}
