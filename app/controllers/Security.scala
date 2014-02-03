package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.libs.json._
import play.api.libs.Files._
import scala.xml._
import helpers.Cache
import helpers.Configuration
import models._

import scala.concurrent.Future
// Import the method statically and rename it
import scala.concurrent.Future.successful
import scala.concurrent.ExecutionContext.Implicits.global

object Security extends Controller {

 
  
   /**
   * Login page.
   */
  def login = Action { request =>
    
    
    
    Ok(views.html.todo("TODO"))
    
  }
  
  /**
   * Logout action.
   */
  def logout = Action { request =>
    
    Ok(views.html.todo("TODO"))
    
  }
  
  /**
   * Login submit. Also used by the client. Takes care of determining is the user is valid.
   * 
   * Sets a session ID in a cookie, and in the memory of the webserver. 
   */
  def authenticate = Action { implicit request =>
    
    val loginForm = Form(
        tuple(
            "username" -> text,
            "password" -> text
        )
    )
    
    val (username, password) = loginForm.bindFromRequest().get
    
    //Check to see if the username exists
    val passwordStuff:Option[(String, String)] = models.database.Databases.getDatabasePlugin.getHashSaltbyUsername(username)
    
    
    var validUser:Boolean = false
    
    passwordStuff match
    {
      case Some((hash:String, salt:String)) =>
        validUser = helpers.Security.hashPassword(password, salt) == hash
        
      case None =>
        validUser = false
        
    }

    //Check the password aginst the hash
    if (validUser)
    {
        var userKey = helpers.Security.genUserKey
        var sessionKey = helpers.Security.genSessionKey
        
        //Put the user into the cache
        	//Get user out of database, and instantiate into an object
        var u:User =models.database.Databases.getDatabasePlugin.getUserByUsername(username)
        //TODO set the sessionKey in the userobject
        
        Cache.setByString(userKey, u, Configuration.getIntOrDefault("security.expire", 3600))
    
	    Ok(views.html.todo("TODO")).withSession(
	        Configuration.getStringOrDefault("security.userkey", Configuration.defaultLoginCookie) -> userKey,
	        Configuration.getStringOrDefault("security.sessionkey", Configuration.defaultSessionCookie) -> sessionKey
	    )
    }
    else
    {
        login.apply(request)
    }



    
  }
  
  
}