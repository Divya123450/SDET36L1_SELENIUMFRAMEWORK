package org.vtiger.Practice;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ValidateDatabaseWrtoGui_RMGyantraTest {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		int randomNumber=new Random().nextInt(100);
		
		String expectedProjectName="doodle"+randomNumber;
		Driver dbDriver=new Driver();
		DriverManager.registerDriver(dbDriver);
		DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root").createStatement().executeUpdate("insert into project values('TY_PROJ"+randomNumber+"','Dinesh','23/06/2022','"+expectedProjectName+"','On Going',0);");
		System.out.println("project added in data base");
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("http://localhost:8084/");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.xpath("//input[@id='usernmae']")).sendKeys("rmgyantra");
		driver.findElement(By.xpath("//input[@id='inputPassword']")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[.='Sign in']")).click();
		driver.findElement(By.xpath("//a[.='Projects']")).click();
		List<WebElement> listofProjects = driver.findElements(By.xpath("//table[@class='table table-striped table-hover']/tbody/tr/td[2]"));
		for(WebElement project:listofProjects) {
			String actualProjectName = project.getText();
			
			if(actualProjectName.equals(expectedProjectName)) {
				System.out.println("project is present in the list of project");
				System.out.println("actual project name-->"+actualProjectName);
				break;
			}
		}
		driver.quit();
	}

}
