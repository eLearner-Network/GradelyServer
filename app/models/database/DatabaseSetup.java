package models.database;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;


public class DatabaseSetup {
	
	/**
	 * Sets up the database for use.
	 * Runs sql files where a line "--END OF STATEMENT" separates the sql statements
	 */
	public static void setUpDatabase(Connection c) throws FileNotFoundException, IOException, SQLException
	{
		//Get a connection
		//Connection c = ConnectionPool.getConnection();
		
		ScriptRunner run = new ScriptRunner(c, true, false);
		run.setDelimiter("--END OF STATEMENT", true);
		
		//Get our SQL Files 
		URL setupUrl = DatabaseSetup.class.getResource("sql/setup.sql");
		
		FileReader r;
		try 
		{
			r = new FileReader(setupUrl.getFile());
			run.runScript(r);
		}
		catch (FileNotFoundException e) 
		{
			throw e;
		} 
		catch (IOException e) 
		{
			throw e;
		}
		catch (SQLException e) 
		{
			throw e;
		}
		
	}

}
