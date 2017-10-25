import java.io.FileWriter;
import java.io.IOException;

/*
 * A class that is a sub class of Student.
 * This class returns students information when called and checks to make sure venus account is valid.
 */
public class QueensCollegeStudent extends Student{

	private String	venusLogin;

	// Overload Constructor
	public QueensCollegeStudent(
			String firstName,
			String lastName,
			int id,
			double gpa,
			int year,
			int month,
			int day,
			String venus)
	{
		// takes data from Student class (base class)
		super(firstName, lastName, id, gpa, year, month, day);
		// Since this attribute is not in base class, must initialize it here
		venusLogin = venus;
	}

	// Used to return value when called
	public String getFirstName()
	{
		return firstName;
	}
	public String getlastName()
	{
		return lastName;
	}
	public int getID()
	{
		return id;
	}
	public int getYear()
	{
		return year;
	}
	public int getMonth()
	{
		return month;
	}
	public int getDay()
	{
		return day;
	}
	public String getVenusLogin()
	{
		return venusLogin;
	}

	/* 
	 * Check to make sure name is valid.
	 * It check the format to make sure it's correct.
	 * 
	 */
	public boolean isValidVenusLogin( )
	{
		String tempID = new String();
		tempID = Integer.toString(id);

		if ( 	firstName.substring(0,2).equals(venusLogin.substring(0,2))&
				lastName.substring(0,2).equals(venusLogin.substring(2,4)) &
				tempID.substring(4).equals(venusLogin.substring(4))  )
			return true;
		else return false;
	}

	String tempGPA = String.valueOf(gpa);

	// Method will print students information
	public void displayAttributes( )
	{
		System.out.println("First Name: \t" + firstName);
		System.out.println("Last Name: \t" + lastName);
		System.out.println("Cuny First ID: \t" + id);
		System.out.printf("GPA: \t\t" + tempGPA.substring(0,3) + "\n");
		
		switch(month)
		{
		case 1:
			System.out.print("Birthday \tJanurary ");
			break;
		case 2:
			System.out.print("Birthday \tFeburary ");
			break;
		case 3:
			System.out.print("Birthday \tMarch ");
			break;
		case 4:
			System.out.print("Birthday \tApril ");
			break;
		case 5:
			System.out.print("Birthday \tMay ");
			break;
		case 6:
			System.out.print("Birthday \tJune ");
			break;
		case 7:
			System.out.print("Birthday \tJuly ");
			break;
		case 8:
			System.out.print("Birthday \tAugust ");
			break;
		case 9:
			System.out.print("Birthday \tSeptember ");
			break;
		case 10:
			System.out.print("Birthday \tOctober ");
			break;
		case 11:
			System.out.print("Birthday \tNovember ");
			break;
		case 12:
			System.out.print("Birthday \tDecember ");
			break;
		}
		System.out.println(day + " " +  year);
		
		System.out.println("Venus Login: \t" + venusLogin);
	}
}