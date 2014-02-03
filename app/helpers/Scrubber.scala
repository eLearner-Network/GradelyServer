package helpers

import play.api.libs.json._
import scala.xml._
import play.api.libs.Files._
import play.api.mvc._

import org.apache.commons.lang3.StringEscapeUtils

/**
 * Takes inputed user data and scrubs it so that it cannot be used in XSS or SGL injection attacks.
 */
object Scrubber 
{

  
  /**
   * Takes a string and returns its scrubbed representation.
   */
  def scrubString(s:String): String = 
  {
    StringEscapeUtils.escapeHtml4(s)
  }
  
  /**
   * Scrubs form data.
   */
  def scrubForm(u:Map[String, Seq[String]]):Map[String, Seq[String]] =
  {
    u.mapValues[Seq[String]](lst => lst.map(v => StringEscapeUtils.escapeHtml4(v)))
  }

  /**
   * Scrubs a file upload with form data.
   */
  def scrubFileUpload(m:MultipartFormData[TemporaryFile]) =
  {
    var data = scrubForm(m.asFormUrlEncoded)
    new MultipartFormData[TemporaryFile](data, m.files, m.badParts, m.missingFileParts)
  }
  
  
}