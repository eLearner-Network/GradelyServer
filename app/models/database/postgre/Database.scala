package models.database.postgre

import play.api._
import play.api.db._
import anorm._
import play.api.Play.current
import java.io.IOException
import java.sql.SQLException
import java.io.FileNotFoundException;

/**
 * Methods for interfaceing with the database.
 */
class Database extends models.database.Database {

  /**
   * Runs sql scripts that set up the database for 
   */
 // def setUpDatabase() = {
    

    
  //}
  
  /**
   * Check to see if a user exists.
   * @returns The hashed password if the user actually exists.
   */
  def getPasswordByUsername(username:String): Option[String]
  {
    
  }
  
}