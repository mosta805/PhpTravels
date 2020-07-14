package utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;



public class Helper {

	Date date;
	public static void captureScreenShot(WebDriver driver , String screenShotName) {


		Path dest = Paths.get("./ScreenShots", screenShotName +".png");
		try {
			Files.createDirectories(dest.getParent());
			FileOutputStream out = new FileOutputStream(dest.toString());
			out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
			out.close();
		} catch (IOException e) {

			System.out.println("Exception While taking ScreenShot : "+ e.getMessage());
		}

	}

	public void verifyLink(String urlLink) {

		try { 

			URL link = new URL(urlLink);		
			HttpURLConnection httpcon = (HttpURLConnection) link.openConnection();
			httpcon.setConnectTimeout(2000);
			httpcon.connect();
			
			date =new Date();

			File file = new File(System.getProperty("user.dir") + "\\src\\test\\java\\data\\HttpResponses.txt"); 
			FileWriter writer =new FileWriter(file,true);
			boolean x = false;

			while( x != true ) {

				if(httpcon.getResponseCode() == 200 || httpcon.getResponseCode() == 201) 
				{
					
					writer.write(urlLink + " : "+ httpcon.getResponseMessage()
					+ " Response Code:"+httpcon.getResponseCode()+" " + date + "\n" );		 
				}


				else if (httpcon.getResponseCode() == 403 || httpcon.getResponseCode() == 404  )
				{
					
					writer.write(urlLink + " : "+ httpcon.getResponseMessage()
					+ " Response Code:"+httpcon.getResponseCode()+" " + date + "\n" );
				}


				else if (httpcon.getResponseCode() == 500 )
				{
					
					writer.write(urlLink + " : "+ httpcon.getResponseMessage()
					+ " Response Code:"+httpcon.getResponseCode()+" " + date + "\n" );
				}

				writer.flush();
				writer.close();
				x = true;
			}


		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}


	}

}
