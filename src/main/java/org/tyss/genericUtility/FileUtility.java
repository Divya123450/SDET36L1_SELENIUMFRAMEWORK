package org.tyss.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {
	private Properties properties;
	
	/**
	 * This method is used for initialize the property file
	 * @param filepath
	 */
	public void initializePrpertyFile(String filepath) {
		FileInputStream fis=null;
		try {
			fis = new FileInputStream(filepath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 properties = new Properties();
		try {
			properties.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	


	

	}
