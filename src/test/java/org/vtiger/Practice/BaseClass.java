package org.vtiger.Practice;

import java.time.Duration;


import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.InstanceClass;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.PropertyFileUtility;
import org.tyss.genericUtility.WebDriverUtility;
import org.vtiger.objectRepository.CommonPage;
import org.vtiger.objectRepository.LoginPage;


public class BaseClass extends InstanceClass{
	@BeforeSuite
	public void suiteSetup() {
		fileUtility = new FileUtility();
		javaUtility = new JavaUtility();
		excelUtility = new ExcelUtility();
	    webDriverUtility = new WebDriverUtility();
		propertyFileUtility=new PropertyFileUtility();
		propertyFileUtility.initializePropertyFile(IConstants.VTIGERPROPERTYFILEPATH);
		url = propertyFileUtility.getDataFromProperty("url");
		un = propertyFileUtility.getDataFromProperty("username");
		pwd = propertyFileUtility.getDataFromProperty("password");
		timeout = propertyFileUtility.getDataFromProperty("timeout");
		browser = propertyFileUtility.getDataFromProperty("browser");
		to = javaUtility.convertStringToLong(timeout);
	}
	@BeforeClass
	public void classSetup() {
		driver = webDriverUtility.setUpDriver(browser);
		webDriverUtility.maximizeBrowser();
		
		webDriverUtility.implicitWait(to);
		
		
		webDriverUtility.initializeActions();
		wait=new WebDriverWait(driver, Duration.ofSeconds(to));
		webDriverUtility.openApplication(url);
		
		commonPage=new CommonPage(driver);
		loginPage=new LoginPage(driver);
		webDriverUtility.openApplication(url);
	
	}
	@BeforeMethod
	public void methodSetup() {
		randomNumber=javaUtility.getRandomNumber();
		
		loginPage.loginAction(un, pwd);
		
	}
	@AfterMethod
	public void methodTearDown() {
		commonPage.logoutAction(webDriverUtility);
	}
	@AfterClass
	public void classTearDown() {
		webDriverUtility.closeBrowser();
	}

}