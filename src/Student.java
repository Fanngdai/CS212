/*
 * The abstract class, meaning it must be inherited by another class,
 * sets the fields, and checks to make sure date of birth is valid
 * by calling the Date class, If it is not, it would set birthday
 * to the initialized date of birth located in the class Date of the
 * empty constructor. This class counts the amount of student generated.
 */

// Student class cannot run by itself
abstract public class Student{
	
	// The amount of student start at 0
	private static int numberOfStudent = 0;

	// Declaring what is needed
	// QueensCollegeStudent Class will need these attributes so they must be declared as protected
	protected String 	firstName;
	protected String 	lastName;
	protected int		id;
	protected double 	gpa;
	protected int 		year;
	protected int	 	month;
	protected int 		day;
	Date birthDate;

	// Overloaded Constructor
	public Student (
			String firstName, 
			String lastName, 
			int id, 
			double gpa, 
			int year, 
			int month, 
			int day)
	{
		// Sets up each student
		this.firstName 	= firstName;
		this.lastName 	= lastName;
		this.id 		= id;
		this.gpa 		= gpa;
		
		// Checks for valid dates
		try
		{
			birthDate = new Date(day, month, year);
		}
		catch (MyInvalidDateException e)
		{
			// If dates are not valid, set it to default date
			birthDate = new Date( );
		}
		
		// Sets a reference to year from type Date class so it can be returned as an integer
		this.year 	= Date.year;
		this.month 	= Date.month;
		this.day  	= Date.day;
		
		// Every time we add a student, variable is incremented
		numberOfStudent++;
	}

	// Used to return each variable
	// If variables are declared private, this is a must!
	public String getFirstName()
	{
		return firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public int getID()
	{
		return id;
	}
	public double getGPA()
	{
		return gpa;
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

	// returns the amount of students
	public static int AmountOfStudent( )
	{
		return numberOfStudent;
	}
}