package helpers

import play.api.Play.current
import play.api.mvc._
import play.api._
import scala.reflect.runtime.universe._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import models.ServerUser

/**
 * Helper methods for getting and setting data from Play's cache.
 */
object Cache {

  /**
   * Puts a value (in this case, an object) into the cache.
   * The key here is a string.
   * @param expiration Time until deleted from cache in sec. 0 means will not expire.
   */
  def setByString(key:String, value:Any, expiration:Int)
  {
    play.api.cache.Cache.set(key, value, expiration)
  }
  
    /**
   * Puts a value (in this case, an object) into the cache.
   * The key here is a string.
   * @param expiration Time until deleted from cache in sec. 0 means will not expire.
   */
  def getAsSimpleResult(key:String):Option[SimpleResult] =
  {
    play.api.cache.Cache.getAs[SimpleResult](key)
  }
  
  
   /**
   * Puts a value (in this case, an object) into the cache.
   * The key here is a string.
   * 
   * The default timeout is 30 min = 1800 sec.
   */
  def setByStringDefaultExpiration(key:String, value:Any)
  {
    play.api.cache.Cache.set(key, value, 1800)
  }
  
  /**
   * Returns true if the key is already in the cache.
   */
  def checkIfExists(key:String):Boolean =
  {
    var result = play.api.cache.Cache.get(key)
    return !result.isEmpty
  }
  
  /**
   * Gets the value out of cache.
   */
  def getUserByString(key:String):Option[ServerUser] =
  {
    
	play.api.cache.Cache.getAs[ServerUser](key)
  }
  
  /**
   * Gets a string if it is the cache.
   */
  def getStringByString(key:String):Option[String] = 
  {
    play.api.cache.Cache.getAs[String](key)
  }
  
  /**
   * Deletes the key-value pair from the cache.
   */
  def delete(key:String)
  {
    play.api.cache.Cache.remove(key)
  }
  

}