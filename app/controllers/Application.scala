package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {
  
  
  
  /**
   * Homepage.
   */
  def index = Action { request =>
    
    Ok(views.html.todo("Index"))
    
  }
  
}