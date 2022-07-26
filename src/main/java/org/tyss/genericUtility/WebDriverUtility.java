package org.tyss.genericUtility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author Divyalochan Sahoo
 *
 */

public final class WebDriverUtility{
	private WebDriver driver;
	private Actions act;
	/**
	 * 
	 * @param browser
	 * @return
	 */
	public WebDriver setUpDriver(String browser) {


		switch (browser) {

		case "chrome":
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			break;
		}
		case "firefox":
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			break;
		}
		case "Internet Explorer":
		{
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();
			break;
		}
		default:
		{
			System.out.println("case not matched");
			break;
		}

		}
		return driver;

	}
	/**
	 * This method is used to maximize the browser
	 */
	public void maximizeBrowser() {
		driver.manage().window().maximize();
	}
	/**
	 * This method is used to wait using implicit wait
	 * @param LongTimeout
	 */
	public void implicitWait(long LongTimeout)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(LongTimeout));
	}
	/**
	 * This method is used to navigate to the application
	 * @param url
	 */
	public void openApplication(String url)
	{
		driver.get(url);
	}
	/**
	 * This method is used to initialize the Actions class
	 */
	public void initializeActions() {
		act=new Actions(driver);
	}
	/**
	 * This method is used to mousehover on element
	 * @param element
	 */
	public void mousehoverOnElement(WebElement element) {
		act.moveToElement(element).perform();
	}
	/**
	 * This method is used to close particular browser
	 */
	public void closeBrowser() {
		driver.quit();
	}
	/**
	 * This method is used to close particular tab
	 */
	public void closeTab() {
		driver.close();
	}
	public void printStatement(String value)
	{
		System.out.println(value);
	}
}

	

