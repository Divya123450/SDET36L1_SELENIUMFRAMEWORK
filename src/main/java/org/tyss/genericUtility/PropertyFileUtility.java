package org.tyss.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * This class contains reusable methods for Csv file and Property file
 * @author Divyalochan Sahoo
 *
 */
public class PropertyFileUtility {
	private Properties properties;
	/**
	 * THis method is used to initialize the property file
	 * @param filePath
	 */
	public void initializePropertyFile(String filePath) {
		FileInputStream fis=null;

		try {
			fis=new FileInputStream(filePath);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		properties=new Properties();
		try {
			properties.load(fis);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	/**
	 * THis method is used to fetch the data from property file
	 * @param key
	 * @return
	 */
	public	String getDataFromProperty(String key) {

		return properties.getProperty(key).trim();
	}
}