package project;

public class BookMyShow extends WebServices
{
	public static void main(String[] args) throws Exception{
						
		try 
		{
			//User Story 1: Book movie ticket
			BookTickets booktickets = new BookTickets();
			booktickets.Test1();
			
			//User Story 2: List your show
			ListYourShow listyourshow = new ListYourShow();
			listyourshow.Test2();
			
			//User Story 3: Login
			Login LoginTest = new Login();
			LoginTest.Test3();

		}
		catch (Exception ex)
		{
			String Error = ex.getMessage();
			ex.printStackTrace();
			System.out.println("Script not Run successfully due to this Error : " + Error);
		}
		
	}

}