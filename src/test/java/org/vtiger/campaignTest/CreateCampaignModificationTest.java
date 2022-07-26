package org.vtiger.campaignTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class CreateCampaignModificationTest {

	public static void main(String[] args) throws IOException {
		
		// TODO Auto-generated method stub
		//Initialize data from property
		FileInputStream fis = new FileInputStream("./src/test/resources/commonData.properties");
		Properties properties = new Properties();
		properties.load(fis);
		
		//Generate the random number
		int randomNumber= new Random().nextInt(1000);
		
		//Fetch the data from property file
		String browser=properties.getProperty("browser").trim();
		String url=properties.getProperty("url").trim();
		String username=properties.getProperty("username").trim();
		String password=properties.getProperty("password").trim();
		String timeouts=properties.getProperty("timeouts").trim();
		
		

	}

}
