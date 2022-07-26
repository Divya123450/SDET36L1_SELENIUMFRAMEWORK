package org.vtiger.contacts;

import java.io.IOException;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.PropertyFileUtility;
import org.tyss.genericUtility.WebDriverUtility;

public class CreateContactWithOrganization {

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
		
		String expectedContactLastName = excelUtility.getDataFromExcel(2, 1, "ORG")+randomNumber;
		
		long longTimeOut=javaUtility.convertStringToLong(timeouts);
		
		
		WebDriver driver=webDriverUtility.setUpDriver(browser);
		
		
		
		webDriverUtility.maximizeBrowser();
		webDriverUtility.implicitWait(longTimeOut);
		webDriverUtility.initializeActions();
		webDriverUtility.openApplication(url);
		
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		int randomNumber1=new Random().nextInt(100);
		String etitle = "xyz"+randomNumber1;
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(etitle);
		WebElement industry = driver.findElement(By.xpath("//select[@name='industry']"));
		Select element=new Select(industry);
		element.selectByValue("Education");
		WebElement type = driver.findElement(By.xpath("//select[@name='accounttype']"));
		Select element1=new Select(type);
		element1.selectByValue("Press");
		driver.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
		
		String eindustry = "Education";
		String etype = "Press";
		
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		String atitle =driver.findElement(By.id("dtlview_Organization Name")).getText();
		String aindustry =driver.findElement(By.xpath("//font[.='Education']")).getText();
		String atype =driver.findElement(By.xpath("//font[.='Press']")).getText();
		
	
		if((etitle+eindustry+etype).equals(atitle+aindustry+atype)){
			System.out.println("pass");
		}
		else {
			System.out.println("fail");
		}
		
		
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(expectedContactLastName);
		driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[1]")).click();
		
		 Set<String> we = driver.getWindowHandles();
		for(String cw:we) {
			driver.switchTo().window(cw);
			if(driver.getCurrentUrl().contains("Accounts")) {
				
				break;
			}
		}
		
		
		 driver.findElement(By.xpath("//a[.='"+etitle+"']")).click();
		 
		 for(String pw:we) {
			 driver.switchTo().window(pw);
				if(driver.getCurrentUrl().contains("Contacts")) {
					
					break;
				}
			}
		
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
		String actualLastName=driver.findElement(By.xpath("//span[@id='dtlview_Last Name']")).getText();
		if(actualLastName.equals(expectedContactLastName)) {
			System.out.println("contact created successfully--->> TC is pass");
		}
		else {
			System.out.println("contact is not created--->> TC is fail");
		}
		
		 WebElement administrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		 webDriverUtility.mousehoverOnElement(administrator);
		 driver.findElement(By.linkText("Sign Out")).click();
		 driver.quit();

	}
}