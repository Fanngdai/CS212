/*
 *  Fanng Dai
 *  Java homework 3
 *  Bryan Lee
 *  section 22
 *  Queens College
 * 
 * This program will ask the user for amount of students to generate. After obtaining a integer,
 * program will randomly generate the first name, last name, cuny id, gpa, and date of birth.
 * The program will check to make sure the venus account made is valid, and that atleast one
 * students birthday is on a leap year and on Feburary 29th. Lastly, it will print each students
 * information and the total amount of students.
 * 
 * There are 5 total classes.
 * 
 * The StudentGenerator class generates the students information randomly. It is where 
 * the main method is located. It will input each students information in an array 
 * called studentInfo. It will then call the displayAttribute method from the Class
 * QueensCollegeStudent to print the students attributes or information.
 */
import java.io.*;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class StudentGenerator
{
	// Make Sure users input is an integer
	public static boolean checkInteger(String userInput)
	{
		// answer = return value
		boolean answer = true;
		try
		{
			// Checks to see if it is an integer
			Integer.parseInt(userInput);
		}
		catch(Exception ex)
		{
			System.exit(0);
		}
		return answer;
	}

	// Checks for leap year
	public static boolean isLeapYear(int year)
	{
		if(year % 4 != 0 && year%100!=0)
			return false;
		else if (year%100==0 && year%400!=0)
			return false;
		else return true;
	}

	// Contain code for randomize attributes
	// Write File
	public static void writeDataToFile(int convertInput)
	{
		try
		{
			// Declare Arrays
			String [] firstName 	= new String[] {"Bob", "Mary", "Peter", "Bryan"};
			String [] lastName 		= new String[]{"Peterson", "Reich", "Jackson", "Lee"};
			int [] cunyID 			= new int[convertInput];
			double [] gpa 			= new double[convertInput];
			int [] month			= new int[convertInput];
			int [] day 				= new int[convertInput];
			int [] year				= new int[convertInput];
			String [] venusLogin 	= new String[convertInput];
			int [] chooseFirstName 	= new int[convertInput];
			int [] chooseLastName 	= new int [convertInput];

			// Randomize Names
			for (int i=0; i < convertInput; i++)
			{
				// 4 options to choose from since there are that many amount of hard coded names in above array
				chooseFirstName[i] = (int)(Math.random( )* firstName.length);
				chooseLastName[i] = (int)(Math.random( )* lastName.length);

				// generates cuny ID (8 random pos digits)
				cunyID[i] = (10000000+(int)(Math.random()*89999999));

				// Generates gpa (double number with only one number after decimal)
				gpa[i] = (Math.random()*4);

				String [] cunyIDString = new String[convertInput];
				String [] cunyIDLastFourNumbers = new String[convertInput];
				cunyIDString[i] = Integer.toString(cunyID[i]);
				cunyIDLastFourNumbers[i] = cunyIDString[i].substring(4,8);

				// Sets up the venus login since we have all information for that student now
				venusLogin[i] = firstName[chooseFirstName[i]].substring(0,2) + 
						lastName[chooseLastName[i]].substring(0,2) + cunyIDLastFourNumbers[i];

				// Some dates generated do not exist therefore, they will be thrown and will use the default birthday.

				// Generates the month
				month[i] = (int)(1+Math.random()*12);
				// Generates the day
				day[i] = (int)(1+Math.random()*31);
				// Generates the birth year from year 1800 to 1999
				year[i] = (int)(1800+Math.random()*200);
				day[i] = (int)(1+Math.random()*29);
			}

			// overrides a birthday to make sure at least one person is born on feb 29
			int chooseLeapYear = (int)(Math.random()*convertInput);
			month[chooseLeapYear]=2;
			day[chooseLeapYear]=29;
			// Keep choosing a year until it's a leap year
			while(!isLeapYear(year[chooseLeapYear]))
			{
				year[chooseLeapYear] = (int)(1000+Math.random()*1016);
			}

			FileWriter fw = new FileWriter("Students.txt");
			// Prints the students attribute
			for (int k = 0; k < convertInput; k++)
			{
				// Writes file
				fw.write(firstName[chooseFirstName[k]] + " ");
				fw.flush();
				fw.write(lastName[chooseLastName[k]] + " ");
				fw.flush();
				fw.write(cunyID[k] + " ");
				fw.flush();
				String tempGPA = String.valueOf(gpa[k]);
				fw.write(tempGPA.substring(0,3) + " ");
				fw.flush();
				fw.write(month[k] + " " + day[k] + " " + year[k] + " ");
				fw.flush();
				fw.write(venusLogin[k]);
				fw.flush();
				fw.write("\n");
				fw.flush();
			}

			// writes amount of students
			fw.write("All " + convertInput +" students have been printed!");
			fw.flush();
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// instantiate Student objects
	// Reads codes from file (writeDataToFile)
	public static void readDataFromFile(int convertInput)
	{
		try
		{
			Scanner StudentFile = new Scanner(new File("Students.txt"));
			QueensCollegeStudent [] studentInfo = new QueensCollegeStudent[convertInput];

			// Splits the names by commas
			String [] StudentFileArray = StudentFile.nextLine().split(" ");
			// Finds the amount of first & last names we have in file (length of array)
			int studentFileLength = StudentFileArray.length;
			int amountOfStudent = studentFileLength/8;

			// Declare Arrays
			String [] firstName 	= new String[convertInput];
			String [] lastName 		= new String[convertInput];
			int [] cunyID 			= new int[convertInput];
			double [] gpa 			= new double[convertInput];
			int [] year				= new int[convertInput];
			int [] month			= new int[convertInput];
			int [] day 				= new int[convertInput];
			String [] venusLogin 	= new String[convertInput];

			for (int i=0; i<amountOfStudent; i++)
			{
				// Set up array for first name
				for (int j=0; j< studentFileLength; j=j+8)
				{
					firstName[i]=StudentFileArray[j];
				}
				for (int j=1; j< studentFileLength; j=j+8)
				{
					lastName[i]=StudentFileArray[j];
				}
				for (int j=2; j< studentFileLength; j=j+8)
				{
					cunyID[i]=Integer.parseInt(StudentFileArray[j]);
				}
				for (int j=3; j< studentFileLength; j=j+8)
				{
					gpa[i]=Double.parseDouble(StudentFileArray[j]);
				}
				for (int j=4; j< studentFileLength; j=j+8)
				{
					month[i]=Integer.parseInt(StudentFileArray[j]);
				}
				for (int j=5; j< studentFileLength; j=j+8)
				{
					day[i]=Integer.parseInt(StudentFileArray[j]);
				}
				for (int j=6; j< studentFileLength; j=j+8)
				{
					year[i]=Integer.parseInt(StudentFileArray[j]);
				}
				for (int j=7; j< studentFileLength; j=j+8)
				{
					venusLogin[i]=StudentFileArray[j];
				}
			}

			// Puts the students together so that they have their identities
			for (int j=0; j<studentInfo.length; j++)
			{
				studentInfo[j] = new QueensCollegeStudent(
						firstName[j], 
						lastName[j], 
						cunyID[j], 
						gpa[j],
						year[j], 
						month[j], 
						day[j], 
						venusLogin[j]);
			}

			for (int k = 0; k < studentInfo.length; k++)
			{
				studentInfo[k].displayAttributes();
			}
		} 
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public static void main (String [] args) throws MyInvalidDateException
	{	
		// Ask user for a number
		// Integer.parseInt will check automatically for numbers
		String userInput = JOptionPane.showInputDialog("How many Students should I instantiate? ");
		// If input is null or input is not a number, exit program
		if (userInput == null | checkInteger(userInput) == false) System.exit(0);
		// Changes input to integer
		int convertInput = Integer.parseInt(userInput);
		// Checks to make sure input is pos else, exit
		if(convertInput<0) System.exit(0);

		writeDataToFile(convertInput);
		readDataFromFile(convertInput);
	}
}