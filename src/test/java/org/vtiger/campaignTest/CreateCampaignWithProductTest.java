package org.vtiger.campaignTest;

import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.PropertyFileUtility;
import org.tyss.genericUtility.WebDriverUtility;

public class CreateCampaignWithProductTest {

	public static void main(String[] args) throws IOException {
		ExcelUtility excelUtility= new ExcelUtility();
		JavaUtility javaUtility=new JavaUtility();
		WebDriverUtility webDriverUtility=new WebDriverUtility();
		PropertyFileUtility propertyFileUtility=new PropertyFileUtility();
		
		propertyFileUtility.initializePropertyFile(IConstants.VTIGERPROPERTYFILEPATH);
		excelUtility.initializeExcelFile(IConstants.VTIGEREXCELFILEPATH);
		
		int randomNumber=  javaUtility.getRandomNumber();
		 
		
		String browser= propertyFileUtility.getDataFromProperty("browser");
		String userName= propertyFileUtility.getDataFromProperty("username");
		String password= propertyFileUtility.getDataFromProperty("password");
		String url= propertyFileUtility.getDataFromProperty("url");
		String timeouts=propertyFileUtility.getDataFromProperty("timeout");
		
		String expectedCampaignName = excelUtility.getDataFromExcel(2, 1, "Campaigns")+randomNumber;
		
		long longTimeOut=javaUtility.convertStringToLong(timeouts);
		
		
		WebDriver driver=webDriverUtility.setUpDriver(browser);
		
		
		
		webDriverUtility.maximizeBrowser();
		webDriverUtility.implicitWait(longTimeOut);
		webDriverUtility.initializeActions();
		webDriverUtility.openApplication(url);
		
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.xpath("(//a[@href='javascript:;'])[1]")).click();
		driver.findElement(By.name("Campaigns")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		driver.findElement(By.name("campaignname")).sendKeys(expectedCampaignName);
		
		driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[1]")).click();
		
		 Set<String> we = driver.getWindowHandles();
		for(String cw:we) {
			driver.switchTo().window(cw);
			if(driver.getCurrentUrl().contains("Accounts")) {
				
				break;
			}
		}
		
		
		 driver.findElement(By.xpath("//a[.='']")).click();
		 
		 for(String pw:we) {
			 driver.switchTo().window(pw);
				if(driver.getCurrentUrl().contains("Contacts")) {
					
					break;
				}
			}
		
		
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
		String actualCampaignName =driver.findElement(By.id("dtlview_Campaign Name")).getText();
		if(expectedCampaignName.equals(actualCampaignName)){
			System.out.println("pass");
		}
		else {
			System.out.println("fail");
		}
		 WebElement administrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		 webDriverUtility.mousehoverOnElement(administrator);
		 driver.findElement(By.linkText("Sign Out")).click();
		 driver.quit();


	}

}
