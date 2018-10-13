/* 
 * Checks to make sure dates are valid.
 * If it is not, then it would be initialized to Jan 1st of 1950.
 * If it is, including valid leap year, then it date is set as is.
 */

public class Date{
	static int month, day, year;

	// Will set birthday as this if their birthday is not valid
	public Date()
	{
		Date.month = 1;
		Date.day = 1;
		Date.year = 1950;
	}

	/** 
	 * Checks to make sure student's birthday is a valid date. If it is not, it will be thrown
	 */
	public Date(int day,int month,int year) throws MyInvalidDateException
	{
		// If month or day is under 1, case will be thrown
		if(day<=0 || month <= 0)
		{
			throw new MyInvalidDateException();
		}
		// Months that have 31 days
		if((month==1 | month==3 | month==5 | month==7 | month==8 | month==10 | month==12) & day>31)
		{
			throw new MyInvalidDateException();
		}
		// Months that have 30 days
		else if((month==4 | month==6 | month==9 | month==11) & day>30)
		{
			throw new MyInvalidDateException();
		}
		// Is not a leap year
		else if(month==2 & StudentGenerator.isLeapYear(year) & day>29)
		{
			throw new MyInvalidDateException();
		}
		// Is a leap year
		else if(month==2 & !(StudentGenerator.isLeapYear(year)) & day>28)
		{
			throw new MyInvalidDateException();
		}
		// If the dates are fine, reference the dates to type Date so we can call it later on
		else
		{
			Date.day = day;
			Date.month = month;
			Date.year = year;
		}
	}
}
