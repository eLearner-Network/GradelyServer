package controllers

import play.api._
import play.api.mvc._

/**
 * Admin control panel and installation
 */
object Admin extends Controller{

  /**
   * Admin control panel.
   */
   def index = Action { request =>
    
	   Ok(views.html.todo("Your new application is ready."))
    
  }
   
   /**
    * So that the admin can change settings
    */
   def settingsPanel = Action {implicit request =>
   
     Ok(views.html.todo("TODO"))
   }
   
   def settingsPanelSubmit = Action {implicit request =>
   
     Ok(views.html.todo("TODO"))
   }
   
   /**
    * Install wizard.
    */
   def install = Action { request =>
     
	   Ok(views.html.todo("TODO"))
     
   }
   
   /**
    * Install wizard form submit.
    */
   def installSubmit = Action { request =>
     
	   Ok(views.html.todo("TODO"))
     
   }
   
  
}