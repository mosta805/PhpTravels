# PhpTravels
Registration page automation 
Project Name : PhPTravels
Deliver Date : 13/7/2020
Description : Automated Test Cases For the registration process
Browsers : Chrome, Firefox 
Framework : Selenium With Maven And TestNg
implemented By : Mostafa Ezz
The task was done using Page Object Model (Pom) design pattern and Data-Driver (DDT) approach.
The project is divided to four main packages:
1) Pages Package
Contains all the UI page for POM classes, it consists of 4 classes
a) BasePage.java
i) Is the parent class which all the other Page classes will inherit from.
ii) The PageFactory.initElements(driver, this); method 
which facilitates the handling of the web elements is initialized in the class constructor so the other classes could inherited it
iii) All the methods and Classes that can be repeated are initialized in the basePage class so the code wouldn’t be repeated in every class Ex(clickButton(WebElement button))

b) HomePage.java
i) Is a sub class which is responsible for initializing and handling all the WebElements in the home page
ii) Different methods created in that class to handle actions that can be done on the homepage WebElements Ex(selectSignUpOption())
iii) Objects created from this class can call the WebElements and methods that were initialized in that class to interact with them not only 
in homepage but also if the WebElements were repeated in any other page

c) LogInPage.java
i) Is a sub class which is responsible for initializing and handling all the WebElements in the LogIn page
ii) A log in methods was created handle sending the log in credentials to the WebElements EX((loginInWithCredentials(String emaiLogIn,String passwordLogIn)))
d) RegistrationPage.java
i) Is a sub class which is responsible for initializing and handling all the WebElements in the Registration page
ii) Two method were created to handle the registration WebElements a method to send the data to the WebElements and another method to clear
the data that was written so when a new data is send it wouldn’t cause any errors
--------------------------
2) Data Package
It contains all the data that needed to execute the test cases and the data that needs to be saved from the execution
a) DataReader.java
i) This class is responsible for handling the date coming from the Excel sheet so it can be used 
ii) The getFileInputStream responsible for locating the excel file 
iii) The public Object[][] getExcelData() is responsible for handling the excel file, open it,
choose the workbook, then the sheet, after that it will loop on all the data using 2D array.

b) UserData.xlsx
i) Excel sheet that contain valid user data so it can be executed in the test cases with the help of the DataReader class

c) HttpResponses.txt
i) This is a txt file that will save the Http response so it can be checked later by the user
---------------------------
3) Test package
Contains all the test cases classes that can be executed
a) BasetTest.java
i) Is the parent class which all the other test classes will inherit from 
ii) All the driver setting are initiated in the @BeforeSuite 
iii) The @AfterSuite is responsible from terminating the driver after all the test cases are executed
iv) The screenShotOnFailuer() method is responsible for receiving and sending the HTTP request

b) RegistrationTest.java
i) All the test cases regarding the registration page are executed in this class

ii) Two approaches of DDT were used to generate the data, the valid user data coming from the excel sheet to that
a successful registration can be done using valid data, and logout and the login with the same credentials again
=---------------------------
4) Utilities Package
Contains the classes that can handle the extra work that can be requested in the project
a) Helper.java
i) Contains two important methods 
ii) The public static void captureScreenShot() responsible for saving the capture screenshot of the failed executed test case 
iii) The verifyLink(String urlLink) for manage and save the coming HTTP request in the txt file
--------------------------------
Important Notes
Steps to generate the TestNg Report:
1) Open the test-output folder
2) The open the Default suite folder
3) RightClick on the default test.html and choose open with web browser

Steps to change the test browser from chrome to Firefox:
1) Open the testing.xml file
2) Change the value in <parameter name="browser" value="chrome"></parameter> from chrome to Firefox
-------------------------------
Limitations
1) The code still need refactoring.
2) The first test case shouldn’t be a @Test it should @BeforeMethod(firstTimeOnly = true),
but when I did it a logical error occurred and I couldn’t fix it before the deadline
3) The test cases are not fully stand-alone they are dependent on each other by using priorities
