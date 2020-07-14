package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


public class BasePage {
	
	protected WebDriver driver;

	public BasePage(WebDriver driver) 
	{

		PageFactory.initElements(driver, this);
	}
	
	protected static void setText(WebElement sendText, String txt) 
	{
		sendText.sendKeys(txt);
	}
	
	protected static void clickButton(WebElement button)
	{
		button.click(); 
	}
	
	protected static void clearTxtBox(WebElement txtBox)
	{
		txtBox.clear();
	}
	
	

}
