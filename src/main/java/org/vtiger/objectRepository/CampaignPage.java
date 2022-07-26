package org.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignPage {
public CampaignPage(WebDriver driver) {
	PageFactory.initElements(driver,this);
	
}
@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
private WebElement createCampaignBtn;

//business library
/**
 * 
 */
public void clickOnCreateCampaignBtn() {
	createCampaignBtn.click();
}
}