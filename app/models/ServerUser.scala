package models

/**
 * This class is stored in memory whenever a user logins to the server.
 */
class ServerUser
(
    sessionKey:String,
    
    /**
     * The boolean is true if it is the default email
     */
    emails:Set[(String, Boolean)]
    
) extends User
{
  

}