package helpers;

/**
 * This defines an exception thrown when we have not gotten around to actually implementing the function.
 * @author Matthew Prichard
 *
 */
public class TodoException extends java.lang.RuntimeException{

	public TodoException()
	{
		super();
	}
	
	public TodoException(String message)
	{
		super(message);
	}
	
}
