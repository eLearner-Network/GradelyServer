package controllers.api.files

import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.data._
import play.api.data.Forms._
import models._
import models.files._
import helpers._

object Metadata extends Controller 
{

   /**
   * Gets the file directorys and other. Returns the file metadata.
   * Takes POST. Returns JSON.
   * 
   * Post variables are:
   * 
   * $ - filepath: Path of the folder or file on the user's filesystem
   * 
   * Query string variables are:
   * 
   * $ - user: Authenticates as the user
   * $ - session: Needs to be paired with the right user key
   * 
   */
  def metadata = LoggedInAction {(request:Request[AnyContent], user:ServerUser) => 
	  {
		//No Login
		//Bad form data
		//Not in database
	    //
	  
		case class Incomeing(filepath:String)
				
		val userForm:Form[Incomeing] = Form(
		mapping(
		"filepath" -> text
		)(Incomeing.apply)(Incomeing.unapply)
		)
		
		var userdata:Option[Incomeing] = FormHelpers.getFormOrNone(userForm)(request)
		
		userdata match
		{
		  case None => BadRequest("Post request was not valid, or did not have all required fields.") 
		}
		
		//Now we have the form data
		//We can use the form data to our advantage
		
		//Look for the file in the database
		var databasePlugin = database.Databases.getDatabasePlugin
		
		var metadata = databasePlugin.getFileMetadata(user, userdata.get.filepath)
		
		//Does the user have view versions permissions?
		var hasReadPermission:Boolean = databasePlugin.hasReadPermission(user.id,file)
		var hasVersionViewPermission:Boolean = databasePlugin.hasViewVersionsPermission(user.id, file)
		
		//Now that we have everything, we can serialize the file metadata.
		
		      	
	  }//:Result
		      	
	} //(Request[AnyContent], ServerUser) => Result //Action[AnyContent]
  
  /**
   * Turns the file metadata object into a JSON representation.
   * Also decides what info to put into the metadata call
   * 
   * 
   */
  def jsonizeFileMetadata(m:FileMetadata, versionViewPermission:Boolean):JsValue = 
  {
    
    Json.obj(
	  "size" -> JsNumber(m.fileResource.size),
	  "revision" -> JsNumber(m.file.currentVersion) 
	  "modified" -> 
	)
    
  }
  
  
}