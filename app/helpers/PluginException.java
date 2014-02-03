package helpers;

/**
 * Defines a Plugin Exception - a problem with the plugins.
 * 
 * @author Matthew Prichard
 *
 */
public class PluginException extends java.lang.Exception 
{

	public PluginException(String message)
	{
		super(message);
	}
	
	public PluginException()
	{
		super();
	}
	
}
