package helpers

import play.api._
import play.api.mvc._
import scala.util._

/**
 * Just some config helpers
 */
object Configuration {
  
  //In this section, we put hard-coded configuration values we might want to refrence
  
  /**
   * Default name of the login cookie on the user's browser
   */
  val defaultUserKeyCookie = "Login.userkey"
  val defaultSessionKeyCookie = "Login.sessionkey"
  
  
  /**
   * Attempts to get configuration from the configuration file. When that fails, resorts to the default value.
   */
  def getStringOrDefault(keyName:String, defaultValue:String):String = 
  {
    val value = Play.current.configuration.getString(keyName)
    
    if(value.isEmpty)
    {
      return defaultValue
    }
    
    return value.get
    
  }
  
    /**
   * Attempts to get configuration from the configuration file. When that fails, resorts to the default value.
   */
  def getIntOrDefault(keyName:String, defaultValue:Int):Int = 
  {
    val value = Play.current.configuration.getInt(keyName)
    
    if(value.isEmpty)
    {
      return defaultValue
    }
    
    return value.get
    
  }
  
  
  def getString(keyName: String):Option[String] = 
  {
      Play.current.configuration.getString(keyName)
  }
  

}