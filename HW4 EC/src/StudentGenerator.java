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

	public static boolean isLeapYear(int year)
	{
		if(year % 4 != 0 && year%100!=0)
			return false;
		else if (year%100==0 && year%400!=0)
			return false;
		else return true;
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

		try
		{
			Scanner firstNameText = new Scanner(new File("firstNameTextFile.txt"));
			Scanner lastNameText = new Scanner(new File("lastNameTextFile.txt"));

			// Splits the names by commas
			String [] firstName 	= firstNameText.nextLine().split(",");
			String [] lastName 		= lastNameText.nextLine().split(",");
			// Finds the amount of first & last names we have in file (length of array)
			int firstNameLength = firstName.length;
			int lastNameLength = lastName.length;

			// Declare of arrays
			int [] cunyID 			= new int[convertInput];
			double [] gpa 			= new double[convertInput];
			int [] month			= new int[convertInput];
			int [] day 				= new int[convertInput];
			int [] year				= new int[convertInput];
			String [] venusLogin 	= new String[convertInput];

			int [] chooseFirstName 	= new int[convertInput];
			int [] chooseLastName 	= new int [convertInput];

			QueensCollegeStudent [] studentInfo = new QueensCollegeStudent[convertInput];

			for (int i=0; i < studentInfo.length; i++)
			{
				// 4 options to choose from since there are that many amount of hard coded names in above array
				chooseFirstName[i] = (int)(Math.random( )*firstNameLength);
				chooseLastName[i] = (int)(Math.random( )*lastNameLength);

				// generates cuny ID (8 random pos digits)
				cunyID[i] = (10000000+(int)(Math.random()*89999999));

				// Generates gpa (double number with only one number after decimal)
				gpa[i] = (Math.random()*4);

				String [] cunyIDString = new String[convertInput];
				String [] cunyIDLastFourNumebrs = new String[convertInput];
				cunyIDString[i] = Integer.toString(cunyID[i]);
				cunyIDLastFourNumebrs[i] = cunyIDString[i].substring(4,8);

				// Sets up the venus login since we have all information for that student now
				venusLogin[i] = firstName[chooseFirstName[i]].substring(0,2) + 
						lastName[chooseLastName[i]].substring(0,2) + cunyIDLastFourNumebrs[i];

				// Some dates generated do not exist therefore, they will be thrown and will use the default birthday.

				// Generates the month
				month[i] = (int)(1+Math.random()*12);
				// Generates the day
				day[i] = (int)(1+Math.random()*31);
				// Generates the birth year from year 1800 to 1999
				year[i] = (int)(1800+Math.random()*200);
			}

			// overrides a birthday to make sure at least one person is born on feb 29
			int chooseLeapYear;
			chooseLeapYear = (int)(Math.random()*convertInput);
			month[chooseLeapYear]=2;
			day[chooseLeapYear]=29;
			// Keep choosing a year until it's a leap year
			while(!isLeapYear(year[chooseLeapYear]))
			{
				year[chooseLeapYear] = (int)(1000+Math.random()*1016);
			}

			// Puts the students together so that they have their identities
			for (int j = 0; j < studentInfo.length; j++)
			{
				studentInfo[j] = new QueensCollegeStudent(	
						firstName[chooseFirstName[j]], 
						lastName[chooseLastName[j]], 
						cunyID[j], 
						gpa[j],
						year[j], 
						month[j], 
						day[j], 
						venusLogin[j]);
			}

			FileWriter fw = new FileWriter("output.txt");
			// Prints the students attribute
			for (int k = 0; k < studentInfo.length; k++)
			{
				int currentAmountOfStudents = k+1;

				/** Prints file */
				studentInfo[k].displayAttributes();
				

				/** Writes file */
				fw.write("First Name: \t" + firstName[chooseFirstName[k]] + "\n");
				fw.flush();
				fw.write("Last Name: \t" + lastName[chooseLastName[k]] + "\n");
				fw.flush();
				fw.write("Cuny First ID: \t" + cunyID[k] + "\n");
				fw.flush();
				String tempGPA = String.valueOf(gpa[k]);
				fw.write("GPA: \t\t" +  tempGPA.substring(0,3) + "\n");
				fw.flush();
				switch(month[k])
				{
				case 1:
					fw.write("Birthday \tJanurary ");
					break;
				case 2:
					fw.write("Birthday \tFeburary ");
					break;
				case 3:
					fw.write("Birthday \tMarch ");
					break;
				case 4:
					fw.write("Birthday \tApril ");
					break;
				case 5:
					fw.write("Birthday \tMay ");
					break;
				case 6:
					fw.write("Birthday \tJune ");
					break;
				case 7:
					fw.write("Birthday \tJuly ");
					break;
				case 8:
					fw.write("Birthday \tAugust ");
					break;
				case 9:
					fw.write("Birthday \tSeptember ");
					break;
				case 10:
					fw.write("Birthday \tOctober ");
					break;
				case 11:
					fw.write("Birthday \tNovember ");
					break;
				case 12:
					fw.write("Birthday \tDecember ");
					break;
				}
				fw.flush();
				fw.write(day[k] + " " +  year[k] + "\n");
				fw.flush();
				fw.write("Venus Login: \t" + venusLogin[k] + "\n");
				fw.flush();
				
				/** Prints and writes file */
				// Checks if the venus login is valid and print accordingly
				if (studentInfo[k].isValidVenusLogin())
				{
					System.out.println(firstName[chooseFirstName[k]] + " " + lastName[chooseLastName[k]] + "'s Venus Login is valid.");
					fw.write(firstName[chooseFirstName[k]] + " " + lastName[chooseLastName[k]] + "'s Venus Login is valid.\n");
					fw.flush();
				}
				else 
				{
					System.out.println(firstName[chooseFirstName[k]] + " " + lastName[chooseLastName[k]] + "'s Venus Login is invalid.");
					fw.write(firstName[chooseLastName[k]] + " " + lastName[chooseLastName[k]] + "'s Venus Login is invalid.\n");
					fw.flush();
				}

				// Prints amount of students so far
				System.out.print("This is the " + currentAmountOfStudents);
				fw.write("This is the " + currentAmountOfStudents);
				fw.flush();
				if(currentAmountOfStudents%10==1 && currentAmountOfStudents!=11) 
				{
					System.out.println("st student out of " + QueensCollegeStudent.AmountOfStudent() + " students!");
					fw.write("st student out of " + QueensCollegeStudent.AmountOfStudent() + " students!\n");
					fw.flush();
				}
				else if(currentAmountOfStudents%10==2 && currentAmountOfStudents!=12)
				{
					System.out.println("nd student out of " + QueensCollegeStudent.AmountOfStudent() + " students!");
					fw.write("nd student out of " + QueensCollegeStudent.AmountOfStudent() + " students!\n");
					fw.flush();
				}
				else if(currentAmountOfStudents%10==3 && currentAmountOfStudents!=13) 
				{
					System.out.println("rd student out of " + QueensCollegeStudent.AmountOfStudent() + " students!");
					fw.write("rd student out of " + QueensCollegeStudent.AmountOfStudent() + " students!\n");
					fw.flush();
				}
				else
				{
					System.out.println("th student out of " + QueensCollegeStudent.AmountOfStudent() + " students!");
					fw.write("th student out of " + QueensCollegeStudent.AmountOfStudent() + " students!\n");
					fw.flush();
				}

				System.out.println( );
				fw.write("\n");
				fw.flush();
			}

			// Prints amount of students
			System.out.println("All " + QueensCollegeStudent.AmountOfStudent() +" students have been printed!");
			fw.write("All " + QueensCollegeStudent.AmountOfStudent() +" students have been printed!");
			fw.flush();
		} 
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}