package org.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewCampaignPage {
public CreateNewCampaignPage(WebDriver driver) {
	PageFactory.initElements(driver,this);
}
@FindBy(xpath="//input[@name='campaignname']")
private WebElement CampaignNamneTextField;
@FindBy(xpath="//input[@title='Save [Alt+S]']")
private WebElement saveBtn;

//business library
/**
 * 
 */
public void createCampaign(String expectedCampaignName) {
	CampaignNamneTextField.sendKeys(expectedCampaignName);
	saveBtn.click();	
}
}