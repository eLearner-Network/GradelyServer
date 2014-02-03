package helpers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.data._
import play.api.data.Forms._
import play.api.data.format.Formats._
import play.api.data.validation._
import play.api.libs.functional.syntax._

object FormHelpers {
  
  
  def getFormOrNone[T](form:Form[T])(implicit request:Request[_]):Option[T] = 
  {
    form.bindFromRequest.fold(
	      errors => {return None},
	      data => {return Some(data)}
	)
    
    
  }

}