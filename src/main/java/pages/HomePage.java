package pages;




import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "div.dropdown.dropdown-login.dropdown-tab")
	public WebElement myAccMenu;

	@FindBy(linkText = "Sign Up")
	WebElement signUpOpt;
	
	@FindBy(linkText = "Login")
	WebElement logInOpt;

	@FindBy(id = "cookyGotItBtnBox")
	public WebElement cookiesMsg;
	
	@FindBy(css = "button.cc-btn.cc-dismiss")
	WebElement cookiesDismissBtn;
	
	@FindBy(linkText = "Logout")
	WebElement logOutOpt;
	
	
	public void dismissCookies()  {
		
		clickButton(cookiesDismissBtn);
	}
	
	
	
	public void selectSignUpOption() {

		clickButton(myAccMenu);
		clickButton(signUpOpt);
	}

	public void selectLogInOption() {

		clickButton(myAccMenu);
		clickButton(logInOpt);
	}
	
	public void selectLogoutOption() {

		clickButton(myAccMenu);
		clickButton(logOutOpt);
	}
	



}
