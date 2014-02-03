package models

/**
 * Compaion object for class.
 */
object Class {
  
  def apply() ={
    
  }
  
   /**
   * Gets class out of database by using an id.
   */
  def apply(id: Int) =
  {
    
  }
  
}


/**
 * Represents a school class.
 */
class Class(
    val id:Int, 
    val userId:Int,
    val startDate:Int,
    val endDate:Int,
    val schoolId:Int,
    val subject:Int,
    val number:String,
    val name:String) {
  
  
  /**
   * Main constructor.
   */
  def apply() = {
    
  }
  
  /**
   * Gets class out of database.
   */
  def apply(subject: String, number: String) = {
    
  }
  
  /**
   * Gets class out of database by using an id.
   */
  def apply(id: Int) =
  {
    
  }
  
}