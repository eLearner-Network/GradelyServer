package controllers.api

import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.data._
import play.api.data.Forms._
import play.api.data.format.Formats._
import play.api.data.validation._
import play.api.libs.functional.syntax._
import models._
import helpers._


/**
 * CRUD and more pertaining to files.
 */
object Files extends Controller {
  
  /**
   * Clients look here for server-side file changes.
   * Comet connection.
   */
  def listen = Action {
    
    
    
    Ok(views.html.todo("TODO"))
    
  }
  
  val echo = Action { request =>
  	Ok("Got request [" + request + "]")
  }
  
  /**
   * Gets the file directorys and other. Returns the file metadata.
   * Takes POST. Returns JSON.
   * 
   * Post variables are:
   * 
   * $ - filepath: Path of the folder or file on the user's filesystem
   * $ - userkey: Authenticates as the user
   * $ - sessionkey: Needs to e paired with the right user key
   * 
   */
  def metadata = Action { implicit request => 
	   
    var uo:Option[User] = helpers.Security.getUserInfo
    
    uo match
    {
      case None => Forbidden("You are not logged in, or are not authorized to view this page.")
    }
    
    var u:User = uo.get
    
    case class Incomeing(filepath:String)
	
    val userForm:Form[Incomeing] = Form(
	  mapping(
	    "filepath" -> text
	  )(Incomeing.apply)(Incomeing.unapply)
	)
	
	var userdata:Option[Incomeing] = FormHelpers.getFormOrNone(userForm)
	
    userdata match
    {
      case None => BadRequest("Post request was not valid, or did not have all required fields.") 
    }
	
    userdata = userdata.get
    
    //Now we have the form data
    //We can use the form data to our advantage
    
    //Look for the file in the database
    
    
    
    

  }
  
  /**
   * Renames a file in the user's box.
   * 
   * Post variables are:
   * 
   * $ - oldfilepath: the previous filepath
   * $ - newfilepath: the new filepath
   * $ - modifiedtime: the time the client noticed the change
   *  
   * 
   * Takes POST. Returns JSON.
   */
  def rename = Action{
    
    //Check if we already have the file
    
    //Update the database

    Ok(views.html.todo("TODO"))
  }
  
   /**
   * The client has signaled a delete. This determines what to do about it.
   * Takes a POST request. Returns 200 ok or error.
   * 
   * Post variables are:
   * 
   * $ - filepath: the filepath of the deleted file.
   * $ - modifiedtime: the time the client noticed the change.
   * 
   */
  def delete = Action{
    
    Ok(views.html.todo("TODO"))
  }
  
  /**
   * The client has seen a new created file. 
   * Takes a POST. Returns JSON.
   * 
   * Post variables are:
   * 
   * $ - filepath: the filepath of the created file.
   * $ - hashsum: The SHA2 32 byte hashsum of the file.
   * $ - size: The size of the file in bytes.
   * $ - isdirectory: boolean. [true|false] True if it is a tirectory
   * $ - modifiedtime: the time the client noticed the change.
   * 
   */
  def create = Action{
    
    Ok(views.html.todo("TODO"))
  }
  
  /**
   * A file has changed at a client. This determines what the client should do. (where to upload the file ect.)
   * 
   * Takes a POST. Returns JSON.
   * 
   * Post variables are:
   * 
   * $ - filepath: the filepath of the edited file.
   * $ - hashsum: The SHA2 32 byte hashsum of the new file.
   * $ - size: The size of the file in bytes.
   * $ - modifiedtime: the time the client noticed the change.
   * 
   * This controllers checks to see if the modified file is already in storage, and if not advises upload.
   * 
   */
  def edit = Action{
    
    Ok(views.html.todo("TODO"))
  }
  
  /**
   * This is called when a client has successfully uploaded/downloaded the file from the storage.
   * Takes POST. Returns 200 Ok or Error.   
   * 
   * Post variables are:
   * 
   * $ - filepath: the filepath of the downloaded file in the user's box folder.
   * $ - downloadtime: How long it took to download the file in sec.
   * $ - remoteurl: The URL of the remote file just downloaded.
   * 
   */
  def success = Action{
    
    Ok(views.html.todo("TODO"))
    
  }
  
  /**
   * This gets called when a client has failed in downloading a file from the remote storage. Used to alert admins.
   * 
   * Post variables are:
   * 
   * $ - filepath: the filepath of the downloaded file in the user's box folder.
   * $ - attemptedtime: How long it took to download the file in sec.
   * $ - percentdownloaded: Percent of the file downloaded. 
   * $ - errormsg: Any error message that could help in troubleshooting. 
   * $ - remoteurl: The URL of the remote file just downloaded.
   * 
   */
  def failure = Action{
    
    Ok(views.html.todo("TODO"))
  }
  
  
   /**
   * Gets revision metadata.
   */
  def revisions = Action {
    
    Ok(views.html.todo("TODO"))
    
  }
  
   /**
   * Searches for files.
   */
  def search = Action {
    
    Ok(views.html.todo("TODO"))
    
  }
  

  

  
  

}