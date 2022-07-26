package org.vtiger.campaignTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;


public class CreateCampaignTest {

public static void main(String[] args) {
		
	// TODO Auto-generated method stub
	
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8888/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).submit();
		Actions a = new Actions(driver);
		WebElement moreOp = driver.findElement(By.xpath("//a[text()='More']"));
		a.moveToElement(moreOp).perform();
		driver.findElement(By.xpath("//a[text()='Campaigns']")).click();
		
		
		driver.findElement(By.xpath("(//a[.='Contacts'])[1]")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys("Sahoo");
		driver.findElement(By.name("button")).click();
		String actual = driver.findElement(By.id("dtlview_Last Name")).getText();
		String expectedText = "Sahoo";
		if(actual.equals(expectedText)) {
			System.out.println("pass");
		}
		else 
			System.out.println("fail");
		Actions a1 = new Actions(driver);
		WebElement src = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		a1.moveToElement(src).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.close();
		
		

}
}
