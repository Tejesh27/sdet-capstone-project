package project;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.io.FileUtils;


public class WebServices

{
	public static WebDriver driver;

	public static RetrieveData database = new RetrieveData();
	
	public void openBrowser() throws InterruptedException
	{

		//Configuring web driver
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\lenovo\\Desktop\\GroupB_GroupG1_CapstoneProject\\My Capstone Project\\chromedriver.exe");

		driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

		//Navigating to BookMyShow website
		driver.get("https://in.bookmyshow.com/city");

		String ActualTitle = driver.getTitle();
		System.out.println("Page Title is : "+ActualTitle);
		
		String ExpectedTitle = "404 Page Not Found : BookMyShow";

		//Validating title of webpage
		if(ExpectedTitle.equals(ActualTitle))
		{
			System.out.println("1. Browser open and Site load successful");
		}
		else
		{
			System.out.println("Fail: Browser Not get open");
		}
	}

	public void TakeScreenshot(String imagename)throws Exception
	{

		//creating an instance of Date and Time
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd__HH_mm_ss");
		LocalDateTime now = LocalDateTime.now();
		String DateandTime = dtf.format(now);
		
		String Path = "C:\\Users\\lenovo\\Desktop\\GroupB_GroupG1_CapstoneProject\\My Capstone Project\\ScreenShots\\" + imagename + "[ " + DateandTime + " ]" + ".png";

        try 
        {
        	//creating TakeScreenshot object from web driver object
        	TakesScreenshot screenshot =((TakesScreenshot)driver);

        	//Call getScreenshotAs method to create image file
        	File SrcFile=screenshot.getScreenshotAs(OutputType.FILE);

        	//Move image file to new destination
        	File DestFile=new File(Path);

        	//Copy file at destination
        	FileUtils.copyFile(SrcFile, DestFile);
			
        	System.out.println("Screen shot Captured and Stored at : " + Path);
        }
        catch (Exception exception)
        {
            System.out.println(exception.getMessage());
        }
	}
}
