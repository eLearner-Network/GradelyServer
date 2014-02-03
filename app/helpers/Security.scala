package helpers

import play.api._
import play.api.mvc._
import play.api.mvc.Results
import views.html._
import models._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import scala.concurrent._

/**
 * Helpers to certify security
 */
object Security 
{
  
  /**
   * The user key uniquely identifies the user to the webserver. This becomes the key the user is stored under.
   */
  def genUserKey: String = 
  {
    var prefix = "login." 
    
    //Log the user in the memory cache
    var r = new scala.util.Random()
    
    var userKey = prefix+r.nextInt.toHexString
    
    while(helpers.Cache.checkIfExists(userKey))
    {
      userKey = prefix+r.nextInt.toHexString
    }
    
    return userKey
  }
  
  
  /**
   * Generates a unique key for use in the cache. 
   */
  def genSessionKey: String = 
  {
    var prefix = "login." 
    
    //Log the user in the memory cache
    var r = new scala.util.Random()
    
    prefix+r.nextInt.toHexString
    
  }
  
  /**
   * Gets the user key for the user's session contained within a cookie for web client users, or in the query string for api users.
   */
  def getUserKey(implicit request:Request[Any]):Option[String] = 
  {
    var cookieName = Configuration.getStringOrDefault("security.userkey", Configuration.defaultUserKeyCookie)
    var cookie = request.cookies.get(cookieName)
    
    cookie match
    {
      case None => request.getQueryString("userkey")
      case Some(c) =>  Some(c.value)
    }
    
  }
  
   /**
   * Gets the session key for the user's session contained within a cookie for web client users, or in the query string for api users.
   */
  def getSessionKey(implicit request:Request[Any]):Option[String] = 
  {
    var cookieName = Configuration.getStringOrDefault("security.userkey", Configuration.defaultUserKeyCookie)
    var cookie = request.cookies.get(cookieName)
    
    cookie match
    {
      case None => request.getQueryString("userkey")
      case Some(c) =>  Some(c.value)
    }
    
  }
  
  
  /**
   * Gets the user info from a cookie in the request, and determines who the user is, and if the user is logged in.
   * @returns Returns an User object pulled from the cache. Returns None if the user is not logged in, or the user is not valid.
   */
  def getUserInfo(implicit request:Request[Any]):Option[ServerUser] =
  {
    var userKey = getUserKey
    var sessionKey = getSessionKey
    
    if(userKey.isEmpty || sessionKey.isEmpty)
    {
      return None
    }
    
    //Check to see if logged in
    var u:Option[ServerUser] = Cache.getUserByString(userKey.get)

    var correctUser = for (i <- u if (i.sessionKey == sessionKey)) yield i
    
    correctUser match 
    {
      case Some(user:ServerUser) => Some(user)
      case None => None
    }


  }
  
  /**
   * Checks to see if this person is a valid user. 
   */
  def isLoggedIn(implicit request:Request[Any]):Boolean =
  {
    var cookieName = Configuration.getStringOrDefault("security.loginkey", "security_login")
    
    var cookie = request.cookies.get(cookieName)
    
    if(cookie.isEmpty)
    {
      return false
    }
    
    val loginKey = cookie.get;
   
    //Check to see if logged in
    if(Cache.checkIfExists(loginKey.value))
    {
      return true
    }
    else
    {
      return false
    }
  }
  
  /**
   * Decides if the user is allowed to see this page. This is used like auth(OK(views.html.my.page))
   * This only decides if the user is logged in, not if the user is allowed to see the page.
   */
  def auth(result:Result)(implicit request:Request[Any]):Result = 
  {
    	if(isLoggedIn)
    	{
    	  result
    	}
    	else
    	{
    	  Results.Forbidden(views.html.error("You do not have permission to access this page. This could be do you being logged out."))
    	}
  
  }

  
  /**
   * Generates a random salt good for storing passwords.
   */
  def generateSalt():String =
  { 
    return BCrypt.gensalt()
  }
  
  /**
   * Turns a password into a secure hash
   * @returns a string containing the password hash.
   */
  def hashPassword(password:String, salt:String): String =
  {
    return BCrypt.hashpw(password, salt)
  }
  
}