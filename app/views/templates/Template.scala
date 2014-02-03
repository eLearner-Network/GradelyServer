package views.templates

import play.api._
import play.api.mvc._

/**
 * Describes everything a template needs to do in order to be a functioning template. Every temlate must implement this Trait.
 */
trait Template {
//To render a page we need to return an object that implements the Content Trait. The Content Trait consists of to fields, content:String, and contentType:String.
// Knows Content subclasses include Html.
  
  /**
   * The unique name for the plugin/template. This is a human readable name. Spaces and case count
   */
  val pluginName = "pluginNameHere";
  
  /**
   * Version number for this template. actually a String
   */
  val version = "0.0.0.1"
  
  /**
   * The fully qualified package and class name of this template. This already has a default implemetation. You probally don't need to do anything.
   */
  //def fullName = this.getClass.getName
    
   /**
   * 501 Not Implemented. The TODO page.
   */
  def todo(message:String):Result

  /**
   * 403 Forbidden page
   */
  def forbidden(message:String):Result

  /**
   * 404 Not Found page
   */
  def notFound(message: String):Result
  
  /**
   * 500 Internal Server Error
   */
  def serverError(message: String):Result
  
  
  
  
  
  
}