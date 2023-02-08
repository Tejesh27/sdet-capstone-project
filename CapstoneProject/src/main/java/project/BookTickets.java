package project;

import org.openqa.selenium.*;


public class BookTickets extends WebServices
{
	public void Test1() throws Exception
	{
		//Open Browser and Load URL
		WebServices service = new WebServices();
		service.openBrowser();

		Thread.sleep(1500);
		String MovieName = database.getDBData("SELECT MovieName FROM bookmyshow WHERE ID=1");
		System.out.println("Movie Name is:" +MovieName);

		//Define web element
		WebElement inputMovieSearchbox1 =
				driver.findElement(By.xpath("//input[@name = 'inputSearchBox'] [@class='search-box typeahead']"));

		inputMovieSearchbox1.sendKeys(MovieName);
		Thread.sleep(500);

		try{
			WebElement inputMovieSearchBox2 = driver.findElement(By.xpath("//input[@class = 'search-box typeahead tt-input']"));

			inputMovieSearchBox2.sendKeys(Keys.ENTER);

		}
		catch (NoSuchElementException exception){
			inputMovieSearchbox1.sendKeys(Keys.ENTER);
		}

		Thread.sleep(1000);

		System.out.println("\nPage title : " +driver.getTitle());

		String CityName = database.getDBData("SELECT City FROM bookmyshow WHERE ID=1");
		Thread.sleep(500);
		System.out.println("\n" + CityName);

		WebElement inputCitySearchbox = driver.findElement(By.xpath("//input[@class = 'sc-kEmuub fhJpTR'] [@placeholder = 'Search for your city']"));
		inputCitySearchbox.sendKeys(CityName);
		Thread.sleep(1000);
		inputCitySearchbox.sendKeys(Keys.ENTER);
		Thread.sleep(1000);

		//Verify the Movie name
		WebElement movieResult = driver.findElement(By.xpath("//h1[@class = 'sc-qswwm9-6 hxbOUw']"));		
		String ActualMovie = movieResult.getText();
		if(MovieName.equals(ActualMovie))
		{
			System.out.println("2. Entered Movie is selected ------  " + ActualMovie);
		}
		
		//Click on Book Ticket button
		Thread.sleep(2000);
		WebElement buttonBookTickets = driver.findElement(By.xpath("//div[@class = 'sc-1vmod7e-2 iBonLL']"));
		buttonBookTickets.click();

		String URLafterbooktickets = driver.getCurrentUrl();
		System.out.println("Site URL after click on Book Tickets button  : " +URLafterbooktickets);
		if(URLafterbooktickets.contains("buytickets"))
		{
			System.out.println("3. Click on Book Tickets successful");
		}
		Thread.sleep(1500);

		//If movie is available in multiple languages
		try{
			WebElement languageOption = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/div[2]/div/div/ul/li[2]/section[2]/div/span"));
			languageOption.click();
		}
		catch (NoSuchElementException exception){
			System.out.println(exception.getMessage());
		}
		Thread.sleep(1500);

		WebElement Alert = driver.findElement(By.xpath("//div[@class = 'wzrk-alert-heading']"));
		String AlertTitle = Alert.getText();
        
        // Display alert Title
        System.out.println("Alert message as:"+AlertTitle);
        Thread.sleep(1000);
  		
        // Accept alert
        WebElement buttonNotnowAlert = driver.findElement(By.xpath("//button[@class = 'No thanks']"));
      	buttonNotnowAlert.click();
        Thread.sleep(1000);
        System.out.println("Alert get closed");
        
        //Open slot for the Next day.
        WebElement linkNextday = driver.findElement(By.xpath("//a[contains(@href,'/tomorrow')]"));
        linkNextday.click();
        Thread.sleep(2000);
        
        //Try to select the First Time slot
        WebElement linkTimeslot1 = driver.findElement(By.xpath("(//a[@class='showtime-pill'])[3]"));
        String ExpectedTimeslotcolor = "rgba(74, 189, 93, 1)";
        String ActualTimeslotcolor = linkTimeslot1.getCssValue("color");
        
        if(ExpectedTimeslotcolor.equals(ActualTimeslotcolor)) 
        {
        	linkTimeslot1.click();
        	Thread.sleep(2000);
        }
        else
        {
        	System.out.println("The Time slot have no seat available");
        }


		//If Terms and Conditions window pop-ups
		try{

			WebElement buttonAcceptTandC = driver.findElement(By.id("btnPopupAccept"));
			buttonAcceptTandC.click();
			Thread.sleep(1000);
		}
		catch (ElementNotInteractableException ex){
			System.out.println("The Terms and Conditions dialog window is enabled for this movie");
		}

		//select the number of seats
        String Numberofseat = database.getDBData("Select NumberOfSeat from bookmyshow Where ID=1");
        String numseat = "//li[@id ='pop_" + Numberofseat + "']";
        WebElement seatCount = driver.findElement(By.xpath(numseat));
        seatCount.click();

        System.out.println("4. Number of seat selected is : " + Numberofseat);
        Thread.sleep(200);
        WebElement buttonSeatSelect = driver.findElement(By.id("proceed-Qty"));
        buttonSeatSelect.click();
        
       
        for(int i =1;i<9;i++)
        {
        	String Seatxpath = "(//a[@class='_available'])[" + i + "]";
        	WebElement Seat = driver.findElement(By.xpath(Seatxpath));
        	Seat.click();
        	Thread.sleep(1000);
        	boolean booleanbuttonPayAmount = driver.findElement(By.xpath("(//a[@id='btmcntbook'][contains(text(),'Pay')])[1]")).isDisplayed();
        
        	if(booleanbuttonPayAmount)
        	{
        		System.out.println("5. Seats are selected.");
        		break;
        	}
        	
        }
        
        //Click on pay Button
        WebElement buttonPayAmount = driver.findElement(By.xpath("(//a[@id='btmcntbook'][contains(text(),'Pay')])[1]"));
        buttonPayAmount.click();
        Thread.sleep(3000);
        System.out.println("6. The Seat is booked and Payment page is open.");
        
        //Take Screen shot
        TakeScreenshot("PaymentPage");

        //Close the Browser.
        Thread.sleep(3000);
		driver.quit();
	
	}

}
