package org.vtiger.Practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteOrganizationTest {

	public static void main(String[] args) throws IOException {
		//initialize data from Property file
		FileInputStream fis = new FileInputStream("./src/test/resources/commonData.properties");
		Properties properties = new Properties();
		properties.load(fis);
		
		//Generate the random number
		int randomNumber = new Random().nextInt(1000);
		
		//get the control for particular sheet in excel
		FileInputStream fisExcel = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook workbook = WorkbookFactory.create(fisExcel);
		Sheet sheet = workbook.getSheet("Organizations");
		
		//Fetch the data from property file 
		String browser=properties.getProperty("browser").trim();
		String url=properties.getProperty("url").trim();
		String username=properties.getProperty("username").trim();
		String password=properties.getProperty("password").trim();
		String timeouts=properties.getProperty("timeouts").trim();
		
		//Fetch the data from Excel file
		String expectedOrganizationName = sheet.getRow(2).getCell(1).getStringCellValue()+randomNumber;
		
		//convert string to long
		long lonTimeout = Long.parseLong(timeouts);
		
		//launching the browser in run time based on browser key
		WebDriver driver=null;
		
		//run time polymorphism
		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup(); //method chaining
			driver=new ChromeDriver();//abstraction
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup(); //method chaining
			driver=new FirefoxDriver();//abstraction
			break;
		case "ie":
			WebDriverManager.iedriver().setup(); //method chaining
			driver=new InternetExplorerDriver();//abstraction
			break;
			default:
				System.out.println("You entered wrong Browser key in Property file");
				
				
				//pre-setting for the browser 
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(lonTimeout));
				
				//create object for explicit wait ,Action class 
				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(lonTimeout));
				
				//navigating the application
				driver.get(url);
				
				//login to the app
				driver.findElement(By.xpath("user_name")).sendKeys("admin");
				driver.findElement(By.xpath("user_password")).sendKeys("admin");
				driver.findElement(By.xpath("submitButton")).click();
				
				
				//create contact
				driver.findElement(By.xpath("//a[.='Organizations']")).click();
				driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
			    driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys("ShaooGroup");
			    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			    
			    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='small']"))));
			    
			    driver.findElement(By.xpath("//a[@class='hdrLink']")).click();
			    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[.='Go to Advanced Search']"))));
		
			    String[] arrPageCount = driver.findElement(By.xpath("//span[@name='Accounts_listViewCountContainerName']")).getText().split(" ");
			    String pageCount1 = arrPageCount[arrPageCount.length-1];
			    
			    driver.findElement(By.xpath("//input[@class='small']")).clear();
			    driver.findElement(By.xpath(""));
			
			
			
			
		}
		

	}

}
