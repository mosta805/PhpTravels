package tests;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import data.DataReader;
import pages.HomePage;
import pages.LogInPage;
import pages.RegistrationPage;
import utilities.Helper;

public class RegistrationTest extends BaseTest {

	HomePage homeObj ; 
	RegistrationPage registObj;
	WebDriverWait wait ;
	Helper helpObj;
	LogInPage logInObj;

	Faker fakeData = new Faker();
	String firstName = fakeData.name().firstName();
	String lastName = fakeData.name().lastName();
	String cellNum = fakeData.phoneNumber().cellPhone();
	String RandomEmail = fakeData.internet().emailAddress();
	String RandomPassword = fakeData.numerify("Abc8#78#");
	String RandomConpassword = fakeData.numerify("Abc8#78#");
	String RandomEmailNew = fakeData.internet().safeEmailAddress();
	String RandomEmailNewest = fakeData.internet().emailAddress();
	String RandomEmailCon = fakeData.internet().emailAddress();
	String RandomEmail1 = fakeData.internet().emailAddress();
	String RandomEmail2 = fakeData.internet().emailAddress();
	String RandomEmail3 = fakeData.internet().emailAddress();
	String RandomEmail4 = fakeData.internet().emailAddress();
	String RandomEmail5 = fakeData.internet().emailAddress();
	String RandomEmail6 = fakeData.internet().emailAddress();
	String RandomEmail7 = fakeData.internet().emailAddress();
	String RandomEmail8 = fakeData.internet().emailAddress();
	String RandomEmail9 = fakeData.internet().emailAddress();
	String RandomEmail10 = fakeData.internet().emailAddress();

	String firstNameNum = fakeData.number().digits(4);
	String lastNameNum = fakeData.number().digits(4);




	@Test(priority = 1)
	public void acceptCookiesOption() {

		homeObj = new HomePage(driver);
		wait = new WebDriverWait(driver, 3);
		wait.until(ExpectedConditions.visibilityOf(homeObj.cookiesMsg));
		homeObj.dismissCookies();

	}

	@DataProvider(name = "ExcelData")
	public Object[][] userRegstData() throws IOException{
		
		DataReader er = new DataReader();
		return er.getExcelData(); 
	}

	

	@Test(priority = 2 , dataProvider = "ExcelData")
	public void userCanRegisterAndLogOutAndLoginAgain(String fName , String lName, String phoneNum , String email , String Password , String ConPassword) throws InterruptedException{

		homeObj = new HomePage(driver);


		homeObj.selectSignUpOption();		
		registObj = new RegistrationPage(driver);
		registObj.submitUserRegisterdData(fName, lName, phoneNum, email, Password, ConPassword);
		
		assertNotEquals(fName, lName);


		wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.urlContains("net/account/"));
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.phptravels.net/account/");

		helpObj = new Helper();
		helpObj.verifyLink(driver.getCurrentUrl().toString()); 

		driver.navigate().back();


		wait.until(ExpectedConditions.elementToBeClickable(homeObj.myAccMenu));
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.phptravels.net/home");

		homeObj.selectLogoutOption();

		logInObj = new LogInPage(driver);

		logInObj.loginInWithCredentials(email, Password);
		wait.until(ExpectedConditions.urlContains("net/account/"));
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.phptravels.net/account/");

		driver.navigate().back();

		wait.until(ExpectedConditions.urlContains("net/home"));

		homeObj.selectLogoutOption();

	}


	@Test(priority = 3)
	public void emptyFirstNameField() {

		homeObj = new HomePage(driver);
		homeObj.selectSignUpOption();
		wait.until(ExpectedConditions.urlContains("/register"));
		registObj = new RegistrationPage(driver);

		registObj.submitUserRegisterdData( " " , lastName, cellNum, RandomEmail, RandomPassword, RandomPassword);
		assertTrue(driver.getCurrentUrl().contains("register"));
		wait.until(ExpectedConditions.visibilityOf(registObj.alartMsg));
		assertTrue(registObj.alartMsg.getText().contains("The First name field is required"));

	}

	@Test(priority = 4)
	public void emptyLastNameField() {

		registObj.clearTxt();	
		registObj.submitUserRegisterdData( firstName , " ", cellNum, RandomEmail, RandomPassword, RandomPassword);		
		wait.until(ExpectedConditions.textToBePresentInElement(registObj.alartMsg, "The Last Name field is required."));
		assertTrue(registObj.alartMsg.getText().contains("The Last Name field is required"));

	}

	@Test(priority = 5)
	public void emptyMobileNumberField() {

		registObj.clearTxt();	
		registObj.submitUserRegisterdData( firstName , lastName, "" , RandomEmail, RandomPassword, RandomPassword); 
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		assertTrue(driver.getCurrentUrl().contains("/register"));

	}

	@Test(priority = 6)
	public void emptyEmailField() {

		registObj.clearTxt();	
		registObj.submitUserRegisterdData( firstName , lastName, cellNum , " ", RandomPassword, RandomPassword);
		wait.until(ExpectedConditions.textToBePresentInElement(registObj.alartMsg ,"The Email field is required."));  
		assertTrue(registObj.alartMsg.getText().contains("The Email field is required."));

	}

	@Test(priority = 7 )
	public void emptyPasswordField() {

		registObj.clearTxt();	
		registObj.submitUserRegisterdData( firstName , lastName, cellNum , RandomEmail, "", RandomPassword); 
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		assertTrue(driver.getCurrentUrl().contains("/register"));

	}

	@Test(priority = 8 )
	public void emptyConPasswordField() {

		registObj.clearTxt();	
		registObj.submitUserRegisterdData( firstName , lastName, cellNum , RandomEmail , RandomPassword,""); 
		assertTrue(driver.getCurrentUrl().contains("/register")); 	
	}

	@Test(priority = 9 )
	public void firstNameFieldFirstValueBlank() {

		homeObj = new HomePage(driver);	        
		registObj = new RegistrationPage(driver);
		registObj.clearTxt();

		registObj.submitUserRegisterdData( " "+ firstName ,lastName, cellNum, RandomEmail9 , RandomPassword, RandomPassword);
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.urlContains("/account")) ;
		assertEquals(driver.getCurrentUrl(), "https://www.phptravels.net/register");	


	}

	@Test(priority = 10 )
	public void lastNameFieldFirstValueBlank() {

		driver.navigate().to("https://www.phptravels.net/home");

		
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.urlContains("/home")) ;
		homeObj = new HomePage(driver);
		homeObj.selectLogoutOption();
		homeObj.selectSignUpOption();	
		wait.until(ExpectedConditions.urlContains("/register"));        
		registObj = new RegistrationPage(driver);
		registObj.submitUserRegisterdData( firstName , " "+lastName, cellNum , RandomEmail10 , RandomPassword, RandomPassword);
		wait.until(ExpectedConditions.urlContains("/account")) ;
		assertTrue(driver.getCurrentUrl().contains("/register")); 	
	}

		
	@Test(priority = 11)
	public void phoneNumberFieldFirstValueBlank() {
		driver.navigate().to("https://www.phptravels.net/home");

		wait.until(ExpectedConditions.urlContains("/home")) ;
		homeObj = new HomePage(driver);
		homeObj.selectLogoutOption();
		homeObj.selectSignUpOption();	

		registObj = new RegistrationPage(driver);
		registObj.submitUserRegisterdData( firstName , lastName, " ", RandomEmailNewest , RandomPassword, RandomPassword);
		wait.until(ExpectedConditions.urlContains("/account")) ;
		assertEquals(driver.getCurrentUrl(), "https://www.phptravels.net/register"); 	
	}

	@Test(priority = 12 )
	public void emailFieldFirstValueBlank() {

		driver.navigate().to("https://www.phptravels.net/home");

		homeObj = new HomePage(driver);
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.urlContains("/home")) ;
		homeObj.selectLogoutOption();
		homeObj.selectSignUpOption();

		homeObj = new HomePage(driver);	        
		registObj = new RegistrationPage(driver);
		registObj.submitUserRegisterdData( firstName , lastName, cellNum ,"  " + RandomEmail , RandomPassword, RandomPassword);
		wait.until(ExpectedConditions.urlContains("/register")) ;
		assertTrue(driver.getCurrentUrl().contains("/register")); 	
	}

	/////////working//////////////

	@Test(priority = 13)
	public void passwordAndConfirmPassFieldsFirstValueBlank() {


		homeObj = new HomePage(driver);	        
		registObj = new RegistrationPage(driver);
		registObj.clearTxt();
		registObj.submitUserRegisterdData( firstName , lastName, cellNum ,RandomEmail1 , " " + RandomPassword," "+ RandomPassword);
		wait.until(ExpectedConditions.urlContains("/account")) ;
		assertTrue(driver.getCurrentUrl().contains("/register")); 	
	}


	@Test(priority = 14)
	public void firstNameFieldAcceptsNumbers() {

		driver.navigate().back();
		driver.navigate().back();

		homeObj = new HomePage(driver);
		homeObj.selectLogoutOption();
		homeObj.selectSignUpOption();

		registObj = new RegistrationPage(driver);

		registObj.submitUserRegisterdData( firstNameNum ,lastName,cellNum, RandomEmail2 ,RandomPassword, RandomPassword);	
		wait.until(ExpectedConditions.urlContains("net/account"));
		assertTrue(driver.getCurrentUrl().contains("/register"));  

	}

	@Test(priority = 15)
	public void lastNameFieldAcceptsNumbers() {

		driver.navigate().back();
		driver.navigate().back();
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.urlContains("/home")) ;
		homeObj = new HomePage(driver);
		homeObj.selectLogoutOption();
		homeObj.selectSignUpOption();

		registObj = new RegistrationPage(driver);

		registObj.submitUserRegisterdData( firstName , lastNameNum,cellNum, RandomEmail3 ,RandomPassword, RandomPassword);	
		wait.until(ExpectedConditions.urlContains("net/account"));
		assertTrue(driver.getCurrentUrl().contains("/register"));  

	}

	@Test(priority = 16)
	public void firstNameFieldAcceptsSpecialCharacters() {

		driver.navigate().to("https://www.phptravels.net/home");


		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.urlContains("/home")) ;
		homeObj = new HomePage(driver);
		homeObj.selectLogoutOption();
		homeObj.selectSignUpOption();

		registObj = new RegistrationPage(driver);

		registObj.submitUserRegisterdData( RandomEmailNew ,lastName,cellNum, RandomEmail6 ,RandomPassword, RandomPassword);	
		wait.until(ExpectedConditions.urlContains("net/account"));
		assertTrue(driver.getCurrentUrl().contains("/register"));  

	}

	@Test(priority = 17)
	public void lastNameFieldAcceptsSpecialCharacters() {

		driver.navigate().to("https://www.phptravels.net/home");

		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.urlContains("/home")) ;
		homeObj = new HomePage(driver);
		homeObj.selectLogoutOption();
		homeObj.selectSignUpOption();

		registObj = new RegistrationPage(driver);

		registObj.submitUserRegisterdData( firstName , RandomEmailNew ,cellNum, RandomEmail4 ,RandomPassword, RandomPassword);	
		wait.until(ExpectedConditions.urlContains("net/account"));
		assertTrue(driver.getCurrentUrl().contains("/register"));  

	}

	@Test(priority = 18)
	public void NumberFieldAcceptsAlphabet() {

		driver.navigate().to("https://www.phptravels.net/home");


		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.urlContains("/home")) ;
		homeObj = new HomePage(driver);
		homeObj.selectLogoutOption();
		homeObj.selectSignUpOption();

		registObj = new RegistrationPage(driver);
		registObj.submitUserRegisterdData( firstName , lastName , firstName, RandomEmail5 ,RandomPassword, RandomPassword);	
		wait.until(ExpectedConditions.urlContains("net/account"));
		assertTrue(driver.getCurrentUrl().contains("/register"));  

	}

	@Test(priority = 19)
	public void emailFieldAcceptsOnlyEmailFormat() {

		driver.navigate().to("https://www.phptravels.net/home");

		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.urlContains("/home")) ;
		homeObj = new HomePage(driver);
		homeObj.selectLogoutOption();
		homeObj.selectSignUpOption();

		registObj = new RegistrationPage(driver);

		registObj.submitUserRegisterdData( firstName , lastName ,cellNum, cellNum ,RandomPassword, RandomPassword);	
		wait.until(ExpectedConditions.urlContains("net/register"));
		assertTrue(driver.getCurrentUrl().contains("/register"));  

	}

	@Test(priority = 20)
	public void passwordFieldLimit() {

		homeObj = new HomePage(driver);	        
		registObj = new RegistrationPage(driver);

		registObj.clearTxt();

		registObj.submitUserRegisterdData( firstName , lastName ,cellNum, RandomEmail6 , firstNameNum, firstNameNum);	
		wait.until(ExpectedConditions.urlContains("net/register"));
		assertTrue(driver.getCurrentUrl().contains("/register"));  	
	}

	@Test(priority = 21)
	public void conPasswordMatchesWithPassword() {

		homeObj = new HomePage(driver);	        
		registObj = new RegistrationPage(driver);

		registObj.clearTxt();
		
		registObj.submitUserRegisterdData( firstName , lastName ,cellNum, RandomEmail8 , RandomPassword, RandomConpassword);	
		wait.until(ExpectedConditions.urlContains("net/register"));
		assertTrue(driver.getCurrentUrl().contains("/register"));  	
	}










}


