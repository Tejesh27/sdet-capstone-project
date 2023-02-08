package project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Login extends WebServices
{
	public void Test3() throws Exception
	{
		//Open Browser and Load URL
		WebServices service = new WebServices();
		service.openBrowser();
		
		//Click on Sign in page
		Thread.sleep(2000);
		WebElement buttonSignin = driver.findElement(By.xpath("//a[@id='preSignIn'][@class='signin']"));
		buttonSignin.click();
		Thread.sleep(3000);
		WebElement inputMobilenumber = driver.findElement(By.xpath("//input[@id='iUserName'][@class='email-input']"));
		if(inputMobilenumber.isDisplayed())
		{
			System.out.println("2. Sign in page is open.");
		}
		
		
		//Enter Mobile number

		String MobileNumber 	= database.getDBData("select PhoneNumber from listyourshow where ID=1");
		inputMobilenumber.sendKeys(MobileNumber);
		
		
		WebElement buttonContinue = driver.findElement(By.xpath("//div[@id='login-loading-state']/span"));
		buttonContinue.click();
		
		
		//30 Seconds time to Enter the Code Manually in the Site.
		Thread.sleep(30000);
		
		
		//verify login
		WebElement verifylogin = driver.findElement(By.xpath("(//span[@id='profileNameId'])[1]"));
		if(verifylogin.isDisplayed())
		{
			System.out.println("3. User is get login Successfully");
		}
		
				
		//Take Screen shot
		Thread.sleep(4000);
		TakeScreenshot("SignInPage");
		
		//Close the Browser.
        Thread.sleep(3000);
		driver.quit();
	}

}
