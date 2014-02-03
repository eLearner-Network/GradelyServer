package models.files

import helpers._
import models._
import models.TypeOfChange

class FileChange(
      /**
   * Id of this object in the database.
   */
  var id: Int,
  
  /**
   * The time the server has confirmed that the file has changed.
   */
  var serverModifiedTime: Time,
  
  /**
   * The time the client portends the the file has changed.
   */
  var clientModifiedtime: Time,
  
  /**
   * Matches up forks when a new fork branches off of a file.
   */
  var previousForkUserFileId: Int,
  
  /**
   * What type of modification was the change?
   */
  var typeOfChange: TypeOfChange
) {


  
  
}