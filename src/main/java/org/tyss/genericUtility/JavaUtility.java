package org.tyss.genericUtility;

import java.util.Random;

/**
 * This class contains java reusable methods
 * @author ASUS
 *
 */
public final class JavaUtility {
	/**
	 * This method is used to generate the Random Number
	 * @param int
	 */
	public int getRandomNumber() {
		return new Random().nextInt(1000);
	}
	/**
	 * This method is used to convert the string to long data type 
	 * @param stringData
	 * @return
	 */
	public long convertStringToLong(String stringData) {
		return Long.parseLong(stringData);
		
	}

	
}
