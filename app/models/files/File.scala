package models.files

/**
 * This class describes a user's file, and links to all of its revisions, changes, and otherwise tracks the evolution of the file.
 * 
 */
class File(
    /**
     * Id of this on the database
     */
    var id:Int,
    
    /**
     * The owner's id on the database
     */
    var userId:Int,
    
    
    var isDeleted:Boolean,
    
    /**
     * The version number of the current file.
     */
    var currentVersion:Int
    
    )
{

}