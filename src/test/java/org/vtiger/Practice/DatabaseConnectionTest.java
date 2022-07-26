package org.vtiger.Practice;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DatabaseConnectionTest {
	
	public static class ValidateGuiWrtoTheDatabase {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		System.out.println("Launch the Browser");
		
		
		driver.get("http://localhost:8084/");
		Random ran = new Random();
		int randomNumber=ran.nextInt(1000);
		
		String projectName = "SDET36_"+randomNumber;
		System.out.println("Project Name==>"+projectName);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//input[@id='usernmae']")).sendKeys("rmgyantra");
		driver.findElement(By.xpath("//input[@id='inputPassword']")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[.='Sign in']")).click();
		System.out.println("successfully login");
		driver.findElement(By.xpath("//a[.='Projects']")).click();
		driver.findElement(By.xpath("//input[@name='projectName']")).sendKeys("projectName");
		driver.findElement(By.xpath("//input[@name='createdBy']")).sendKeys("Sahoo");
		WebElement ProjectstatusDropdown = driver.findElement(By.xpath("//label[.='Project Status ']/following-sibling::select"));
		Select select = new Select(ProjectstatusDropdown);
		select.selectByVisibleText("on Going");
		
		driver.findElement(By.xpath("//input[@class='btn btn-success']")).click();
		System.out.println("Successfully Created project");
		
		Driver dbDriver = new Driver();
		DriverManager.registerDriver(dbDriver);
		ResultSet result = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root").createStatement().executeQuery("select* from project");
		
		while(result.next()) {
			if(result.getString("project_name").equals("projectName")) {
				System.err.println("project is present in database");
				System.out.println("Actual Project Name==>>>"+result.getString("project_name"));
			}
		}
		driver.quit();
		
		

	}

	}
}
