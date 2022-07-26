package org.vtiger.Organizations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationTest {

	public static void main(String[] args) throws IOException {
		
		// initialize data from Property file
		FileInputStream fis = new FileInputStream("./src/test/resources/commonData.properties");
		Properties properties = new Properties();
		properties.load(fis);
		
		//Generate the random number
		int randomNumber = new Random().nextInt(1000);
		
		//Fetch the data from property file 
		String browser=properties.getProperty("browser").trim();
        String url=properties.getProperty("url").trim();
		String username=properties.getProperty("username").trim();
		String password=properties.getProperty("password").trim();
		String timeouts=properties.getProperty("timeout").trim();
		long longTimeout = Long.parseLong(timeouts);
		
		//get the control for particular sheet in excel
		FileInputStream fisExcel = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook workbook = WorkbookFactory.create(fisExcel);
		Sheet sheet = workbook.getSheet("Sheet1");
		String expectedOrganizationName = sheet.getRow(2).getCell(1).getStringCellValue()+randomNumber;
				
		
		//run time polymorphism
		WebDriver driver = new ChromeDriver();
		
		//pre-setting for the browser 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(longTimeout));
			
		//navigating the application
		driver.get(url);
		
		//creating object for Actions class
		Actions act = new Actions(driver);
		
		//login to the app
		driver.findElement(By.xpath("user_name")).sendKeys("admin");
		driver.findElement(By.xpath("user_password")).sendKeys("admin");
		driver.findElement(By.xpath("submitButton")).click();
		driver.findElement(By.xpath("//a[.='Organizations']")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys();
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='small']"))));
		String actualOrganisationName = driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).getText();
		
		//validate the data
		if(actualOrganisationName.equals(expectedOrganizationName)) {
			System.out.println("Organization created successfully---->TC is Pass");
			System.out.println("Actual organization name==>"+actualOrganisationName);
			FileOutputStream fos = new FileOutputStream("./src/test/resources/Testdata.xlsx");
			workbook.write(fos);
			System.out.println("Data entered");
		}
		else {
			System.out.println("Organization not created successfully---->TC is Fail");
			sheet.getRow(2).createCell(4).setCellValue("Fail");
			FileOutputStream fos = new FileOutputStream("./src/test/resources/Testdata.xlsx");
			workbook.write(fos);
			System.out.println("Data entered");
			}
		
		//Close the workbook and driver
		WebElement wb1 = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		act.moveToElement(wb1).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
		
		}
		

	}


