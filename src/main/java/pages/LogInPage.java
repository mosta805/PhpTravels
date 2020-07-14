package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogInPage extends BasePage {
	public LogInPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(name = "username")
	WebElement emailLogIntxt;
	
	@FindBy(name = "password")
	WebElement passworLogIntxt;

	@FindBy(css = "button.btn.btn-primary.btn-lg.btn-block.loginbtn")
	WebElement logInSubmitBtn;
	
	public void loginInWithCredentials(String emaiLogIn , String passwordLogIn) {
		setText(emailLogIntxt, emaiLogIn);
		setText(passworLogIntxt, passwordLogIn);
		clickButton(logInSubmitBtn);
	}
}
