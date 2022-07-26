package org.vtiger.Practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateProjectValidation {

	public static void main(String[] args) throws SQLException {
		int randomNumber1=new Random().nextInt(100);
		String expectedProjectName = "SDET36_7"+randomNumber1;
		System.out.println("expected project name==>>"+expectedProjectName);
		Driver driver= new Driver();
		DriverManager.registerDriver(driver);
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
		Statement statement1 = connection.createStatement();
		statement1.executeUpdate("insert into project values('TY_PROJ_007"+randomNumber1+"','mohan','23/06/2022','"+expectedProjectName+"','On Going',7);");
		System.out.println("project added into database successfully");
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver1 = new ChromeDriver();
		driver1.manage().window().maximize();
		driver1.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver1.get("http://localhost:8084");
		Random ran= new Random();
		int randomNumber=ran.nextInt(1000);
		String projectName="SDET36_"+randomNumber;
		driver1.findElement(By.xpath("//input[@id='usernmae']")).sendKeys("rmgyantra");
		driver1.findElement(By.xpath("//input[@id='inputPassword']")).sendKeys("rmgy@9999");
		driver1.findElement(By.xpath("//button[.='Sign in']")).click();
		System.out.println("successfully login");
		driver1.findElement(By.xpath("//a[.='Projects']")).click();
		 List<WebElement> listOfProjects = driver1.findElements(By.xpath("//table[@class='table table-striped table-hover']/tbody/tr/td[2]"));
		 for(WebElement project:listOfProjects) {
			 String actualProjectName=project.getText();
			 if(actualProjectName.equals(expectedProjectName)) {
				 System.out.println("project is present in the list of projects page");
				 System.out.println("actual project name==>>"+actualProjectName);
				 break;
			 }
		 }
		driver1.findElement(By.xpath("//button[@class='btn btn-success']")).click();
		driver1.findElement(By.xpath("//input[@name='projectName']")).sendKeys(projectName);
		driver1.findElement(By.xpath("//input[@name='createdBy']")).sendKeys("anil");
		WebElement projectStatusDropDown= driver1.findElement(By.xpath("//label[.='Project Status ']/following-sibling::select"));
		Select select=new Select(projectStatusDropDown);
		select.selectByVisibleText("On Goging");
		driver1.findElement(By.xpath("//input[@class='btn btn-success']")).click();
		System.out.println("successfully created project");
		
		Driver driver2= new Driver();
		DriverManager.registerDriver(driver2);
		Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
		Statement statement = connection1.createStatement();
		ResultSet result = statement.executeQuery("select * from project;");
		while (result.next()) {
			if(result.getString("project_name").equals(projectName)) {
				System.out.println("project is present in the data base");
				System.out.println("actual project name==>>>"+result.getString("project_name"));
			}
			
		}
		connection.close();
		driver1.quit();
		}
	}