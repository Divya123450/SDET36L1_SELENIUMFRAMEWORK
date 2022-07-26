package org.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.tyss.genericUtility.WebDriverUtility;

public class CommonPage {
public CommonPage(WebDriver driver) {
	PageFactory.initElements(driver,this);
} 
public void CommonPage1(WebDriver driver) {
	// TODO Auto-generated constructor stub
}
@FindBy(xpath="//a[.='More']")
private WebElement moreTab;

@FindBy(xpath="//a[@name='Campaigns']")
private WebElement campaignsTab;

@FindBy(xpath="//img[@title='Create Campaign...']")
private WebElement createCampaignTab;

@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
private WebElement adminstratorTab;

@FindBy(xpath="//a[.='Sign Out']")
private WebElement signoutBtn;
/**
 * This method is used to click on campaign tab in common page
 * @param webdriverUtility
 */
public void clickCampaign(WebDriverUtility webdriverUtility) {
	webdriverUtility.mousehoverOnElement(moreTab);
	campaignsTab.click();
}
/**
 * This method is used to signout from the application
 * @param webdriverUtility
 */
public void logoutAction(WebDriverUtility webdriverUtility) {
	webdriverUtility.mousehoverOnElement(adminstratorTab);
	signoutBtn.click();
}
}