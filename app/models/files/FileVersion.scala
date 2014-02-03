package models.files

import helpers._

/**
 * Describes a file on the user file system. This Class describes a single revision of the file. 
 */
class FileVersion(
   /**
   * Id of this folder
   */
  var id: Int,
  
  /**
   * Allows us to match up previous versions of a file.
   */
  var userFileId: Int,
 
  
  /**
   * Links this file to a file resource that can be downloaded. 
   */
  var fileResourceId: Int,
  
  /**
   * The id of the file change that created this file.
   */
  var creatingChangeId: Int,
  
  /**
   * The higher the version number, the more recent the file is.
   */
  var version: Int,
  
  /**
   * True if the user has deleted this file on his/her local filesystem.
   */
  var isDeleted: Boolean,
  
  /**
   * True if the file is actually folder! GASP!
   */
  var isDirectory: Boolean,
  
  /**
   * The filepath of the file within the user's box folder.
   */
  var filepath: String
   
)
{
  
  

}