package models.database

import models._
import models.files._
import models.files.FileMetadata

/**
 * The trait/interface that describes what any database implemetation should be able to do.
 */
trait Database 
{
  
  /**
   * The name of the plugin
   */
  var pluginName:String
  
  /**
   * Version of this plugin
   */
  var pluginVersion:String
  
  /**
   * The type of database that this plugin accesses
   * Examples:
   * $ - Postgre
   * $ - MySql
   * $ - Oracle
   * $ - Couch
   */
  var databaseType:String
  
  /**
   * Most recent version of the database that the plugin works with.
   */
  var databaseVersion:String

  
  
  /**
   * Sets up the database from scratch.
   */
  def setUpDatabase(): Boolean
  
  /**
   * Check to see if a user exists.
   * @returns The hashed password and the salt if the user actually exists. 1 is the password, 2 is the salt
   */
  def getHashSaltbyUsername(username:String): Option[(String, String)]
  
    /**
   * Gets a user out of the database by the user's username
   * @returns a filled out, instantiated user object, or None if the user does not exist
   */
  def getUserByUsername(username:String):Option[User]
  
  /**
   * Gets the Ids of each class the user is in.
   */
  def getClassIds(user:User):Set[Int]
  
  /**
   * Gets file metadata for a file from a 
   */
  def getFileMetadata(user:User, filepath:String): Option[FileMetadata]

  def hasWritePermission(userId: Int, file:File)
  
   /**
   * Determines if the user has permission to view old versions of the file and their respective changes.
   * checks all three versions at once.
   * @param file The appropriate file with its ID
   * @returns True if the user explicity has the view version permission 
   */
  def hasReadPermission(userId: Int, file:File)
  
   /**
   * Determines if the user has permission to view old versions of the file and their respective changes.
   * checks all three versions at once.
   * @param file The appropriate file with its ID
   * @returns True if the user explicity has the view version permission 
   */
  def hasViewVersionsPermission(userId: Int, file:File)
  
  /**
   * Determines if the user has permission to view old versions of the file and their respective changes.
   * Only checks if the permission is given for the user.
   * @param file The appropriate file with its ID
   * @returns True if the user explicity has the view version permission 
   */
  def hasViewVersionsUserPermission(userId: Int, file:File):Boolean
  
   /**
   * Determines if the user has permission to view old versions of the file and their respective changes.
   * Only checks if the permission is given for the class.
   * @param file The appropriate file with its ID
   * @returns True if the user explicity has the view version permission 
   */
  def hasVewVersionsClassPermission(classId: Int, file:File):Boolean
  
   /**
   * Determines if the user has permission to view old versions of the file and their respective changes.
   * Only checks if the permission is given for the school.
   * @param file The appropriate file with its ID
   * @returns True if the user explicity has the view version permission 
   */
  def hasVewVersionsSchoolPermission(schoolId: Int, file:File):Boolean


}