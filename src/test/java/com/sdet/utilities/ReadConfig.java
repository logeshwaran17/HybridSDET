package com.sdet.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	public Properties prop;
	public ReadConfig() {
		
		File src=new File("./Configuration/config.properties");
		
		try {  // to try if its loaded properly
		
			FileInputStream fis=new FileInputStream(src);
			prop=new Properties();
			prop.load(fis);
			
			
		} catch (Exception e) {
			System.out.println("reason is "+e.getMessage());
		}
	}
	
	public String getApplicatonURL() {
		String url=prop.getProperty("baseURL");
		return url;

	}
	
	public String getUserName() {
		String userName=prop.getProperty("username");
		return userName;

	}
	public String getPassword() {
		String password=prop.getProperty("password");
		return password;

	}
	
}
