package controllers.api.assignments


import play.api._
import play.api.mvc._


/**
 * CRUD actions for assignments.
 */
object assignments extends Controller {
  
  /**
   * Gets
   * Takes a POST request. Returns JSON.
   */
  def get = Action{
    
    Ok(views.html.todo("TODO"))
  }
  
   /**
   * Deletes
   * Takes a POST request. Returns 200 ok or error.
   */
  def delete = Action{
    
    Ok(views.html.todo("TODO"))
  }
  
  /**
   * Creates
   * Takes a POST. Returns JSON.
   */
  def create = Action{
    
    Ok(views.html.todo("TODO"))
  }
 
  
  /**
   * Edits
   * Takes a POST. Returns JSON.
   */
  def edit = Action{
    
    Ok(views.html.todo("TODO"))
  }
  

  
  

}