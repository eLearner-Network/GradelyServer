package models.database

/**
 * This class is the starting point for all database transactions. This class loads the proper database plugin and creates refrences to the proper Database object
 * It loads configuration out of the standard Play config file. 
 */
object Databases
{

  /**
   * Gets the proper Database object. 
   */
  def getDatabasePlugin(): Database = 
  {
    //TODO 
    throw new helpers.TodoException()
  }

  
}