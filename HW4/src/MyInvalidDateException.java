/*
 * An exception class. Used when date is not correct and is needed to be changed.
 * Follows number 9 in instructions.
 */

@SuppressWarnings("serial")
public class MyInvalidDateException extends Exception
{
	Date birthdate = new Date();
	
	// We don't need this since we inherited
	public MyInvalidDateException()
	{
		super();
	}
	public MyInvalidDateException(String message)
	{
		// Calls base class with a string
		super(message);
	}
}