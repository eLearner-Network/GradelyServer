package models.files

/**
 * Encapsulates the file metadata on a version of the file. This is pretty much everything we need to know about the file.
 * Wraps up File, FileChange, FileResource, and FileVersion classes.
 */
class FileMetadata(
	/**
	 *  File used to link-up all file versions
	 */	
    val file:File,
    
    /**
     * the most recent version of the file
     */
    val fileVersion:FileVersion,
    
    /**
     * the change that created the most recent version.
     */
    val fileChange:FileChange,
    
    /**
     * How to get the file contents
     */
    val fileResource:FileResource
    
)
{
  
  

}