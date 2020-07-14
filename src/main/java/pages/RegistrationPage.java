package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage{

	public RegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(name = "firstname")
	public WebElement firstNameTxt;
	
	@FindBy(name = "lastname")
	WebElement lastNameTxt;
	
	@FindBy(name = "phone")
	WebElement phoneTxt;
	
	@FindBy(name = "email")
	WebElement emailTxt;
	
	@FindBy(name = "password")
	WebElement passwordTxt;
	
	@FindBy(name = "confirmpassword")
	WebElement ConpasswordTxt;
	
	@FindBy(css = "button.signupbtn.btn_full.btn.btn-success.btn-block.btn-lg")
	WebElement submitBtn; 
	
	@FindBy(css ="div.alert.alert-danger")
	public WebElement alartMsg ;
	
	
	public void submitUserRegisterdData(String firstName,String lastName,String phoneNum,String email,String Password,String ConPassword) {		
		setText(firstNameTxt, firstName);
		setText(lastNameTxt, lastName);
		setText(phoneTxt, phoneNum);
		setText(emailTxt, email);
		setText(passwordTxt, Password);
		setText(ConpasswordTxt, ConPassword);
		clickButton(submitBtn);

	}
	
	public void clearTxt() {
		
		clearTxtBox(firstNameTxt);
		clearTxtBox(lastNameTxt);
		clearTxtBox(phoneTxt);
		clearTxtBox(emailTxt);
		clearTxtBox(passwordTxt);
		clearTxtBox(ConpasswordTxt);
		
		
	}

}
