package models.files

/**
 * This describes where the actual bits of the file are.
 */
class FileResource(
    id:Int,
    
    /**
     * SHA256 hashsum of the file
     */
    hashsum:String,
    
    /**
     * Size of the file in bytes.
     * The current limitation on file sizes is at max of the Int at 2.1 GiB
     */
    size:Long,
    
    /**
     * If this records a diff file instead of the full file text
     */
    isDiff:Boolean,
    
    /**
     * The resource id of the previous diff.
     */
    diffFromFileResourceId:Int,
    
    /**
     * The base64 encoded string.
     */
    encryptionKey:String
    
) {

}