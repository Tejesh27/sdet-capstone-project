package project;

import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class ListYourShow extends WebServices
{
	public void Test2() throws Exception
	{
		//Open Browser and Load URL
		WebServices service = new WebServices();
		service.openBrowser();

		Thread.sleep(1500);

		//click on list your show button on landing page
		try{
			WebElement linkListYourShow = driver.findElement(By.xpath("//a[@href = '/list-your-show/'] [contains(text(),'ListYourShow')]"));
			linkListYourShow.click();
		}
		catch (NoSuchElementException e){
			WebElement listYourShow = driver.findElement(By.xpath("//a[@class = 'nav-link menu-item'] [contains(text(),'ListYourShow')]"));
			listYourShow.click();
		}


		Thread.sleep(2000);

		String ActualListpageURL = driver.getCurrentUrl();

		String ExpectedListpageURL = "https://in.bookmyshow.com/list-your-show/";

		if(ExpectedListpageURL.equals(ActualListpageURL))
		{
			System.out.println("2. Navigate to LIST YOUR PAGE successfully.\nURL is :"+driver.getCurrentUrl());
		}

		Thread.sleep(500);

		//Enter City name and Select in popup
		WebElement inputCitySearchbox = driver.findElement(By.xpath("//input[@class = 'sc-kEmuub fhJpTR'] [@placeholder = 'Search for your city']"));
		String CityName = database.getDBData("SELECT City FROM bookmyshow WHERE ID=1");
		Thread.sleep(500);
		inputCitySearchbox.sendKeys(CityName);
		Thread.sleep(500);
		inputCitySearchbox.sendKeys(Keys.ENTER);
		Thread.sleep(1000);

		//click on list your show page in list your show page
		WebElement buttonListyourshow = driver.findElement(By.xpath("(//button[@type ='default'][@class='sc-1y2oebh-1 iRpfzT   '])[1]"));
		buttonListyourshow.click();

		//click on list your show on popup
		Thread.sleep(2000);
		System.out.println("3. Filling Details popup is get open");

		System.out.println("Before 2nd list your show");
		try {
			WebElement popupbuttonListyourshow2 = driver.findElement(By.xpath("(//p[@class='sc-1pz8rb6-1 ebjpiw'][contains(text(),'List your show')])[3]"));
			popupbuttonListyourshow2.click();
		}
		catch (NoSuchElementException ex){

		}
		
		//Fill the form with data stored in database.

		String YourName 		= database.getDBData("select YourName from listyourshow where ID=1");
		WebElement formName = driver.findElement(By.id("name-field"));
		formName.sendKeys(YourName);

		String Email 			= database.getDBData("select Email from listyourshow where ID=1");
		WebElement formEmailID = driver.findElement(By.id("email-field"));
		formEmailID.sendKeys(Email);
		Thread.sleep(500);

		String MobileNumber 	= database.getDBData("select PhoneNumber from listyourshow where ID=1");
		WebElement formPhoneNumber = driver.findElement(By.id("number-field"));
		formPhoneNumber.sendKeys(MobileNumber);

		String Region 			= database.getDBData("select Region from listyourshow where ID=1");
		Select formRegion = new Select(driver.findElement(By.id("region-select")));
		formRegion.selectByVisibleText(Region);
		Thread.sleep(500);

		String EventCity 		= database.getDBData("select EventCity from listyourshow where ID=1");
		Select formEventCity = new Select(driver.findElement(By.id("city-select")));
		formEventCity.selectByVisibleText(EventCity);

		String EventType 		= database.getDBData("select EventType from listyourshow where ID=1");
		Select formEventType = new Select(driver.findElement(By.id("eventtype-select")));
		formEventType.selectByVisibleText(EventType);
		Thread.sleep(500);

		String Audience 		= database.getDBData("select Audience from listyourshow where ID=1");
		Select formAudience = new Select(driver.findElement(By.id("density-select")));
		formAudience.selectByVisibleText(Audience);

		String Date 			= database.getDBData("select Date from listyourshow where ID=1");
		Select formEventDate = new Select(driver.findElement(By.id("date-select")));
		formEventDate.selectByVisibleText(Date);
		Thread.sleep(500);

		String EventDiscription	= database.getDBData("select Description from listyourshow where ID=1");
		WebElement formDescription = driver.findElement(By.id("description-field"));
		formDescription.sendKeys(EventDiscription);
		
		WebElement buttonSubmitonListyourshow = driver.findElement(By.xpath("//div[@class='sc-zgl7vj-7 kEWfhF']"));

		if(buttonSubmitonListyourshow.isEnabled())
		{
			System.out.println("4. All the content of the form is filled successfully");
		}
		Thread.sleep(300);
		
		//Click on Submit button
		buttonSubmitonListyourshow.click();
		Thread.sleep(5000);
		
		//get Window handle
		
		 String mainWindowHandle = driver.getWindowHandle(); 

		
		//click on Share button
		WebElement buttonShare = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div[5]/div[1]"));
		buttonShare.click();
		Thread.sleep(2000);

		WebElement linkTwitter = driver.findElement(By.xpath("//span[@class = 'sc-1mqgrh0-2 kKVcQO'][contains(text(),'Twitter')]"));
		linkTwitter.click();
		Thread.sleep(2000);
		
		//Switch to new open page
		Set <String> allWindowhandles = driver.getWindowHandles(); 
		Iterator<String> iterator = allWindowhandles.iterator();
		 
		 // Here we will check if child window has other child windows and will fetch the heading of the child window 
		 while (iterator.hasNext()) 
		 { 
			 String ChildWindow = iterator.next(); 
			 if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) 
			 {
				 driver.switchTo().window(ChildWindow);
				 System.out.println(ChildWindow);
			 }
		  
		 }
				
		//Take Screen shot
		Thread.sleep(3000);
		TakeScreenshot("ShareOnTwitter");
		
		//Close the Browser
        Thread.sleep(4000);
		driver.quit();
				
	}
}