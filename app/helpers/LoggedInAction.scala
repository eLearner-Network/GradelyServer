package helpers

import play.api._
import play.api.mvc._
import scala.concurrent.Future
import scala.concurrent.Future.successful
import scala.concurrent.ExecutionContext.Implicits.global

import models._


    	/**
	 * This is an action that will only display the page if the user is properly logged in. 
	 * Also gets the user out of the cache so that we can work with the 
	 * Used like:
	 * def foo = LoggedInAction {(implicit result:Request[A], user:ServerUser) ==> ...
	 */
	object LoggedInAction extends ActionBuilder[Request] 
	{
  
		def composeAction[A](action: Action[A]): Action[A] = action
  
		
		/**
		 * Also needed to modify the input parameters to our action block.
		 */
		def async[A](bodyParser: BodyParser[A])(block: (Request[A],ServerUser) => Future[SimpleResult]): Action[A] = composeAction(
		   new Action[A] 
		   {
				def parser = composeParser(bodyParser)
				
				def apply(request: Request[A]) = 
					try 
					{
					  invokeBlock(request, block)
					} 
					catch 
					{
					  // NotImplementedError is not caught by NonFatal, wrap it
					  case e: NotImplementedError => throw new RuntimeException(e)
					  // LinkageError is similarly harmless in Play Framework, since automatic reloading could easily trigger it
					  case e: LinkageError => throw new RuntimeException(e)
					}
				
				override def executionContext = LoggedInAction.this.executionContext
		    }
		)
  
		  /**
		   * This function is needed so that we can modify the input parameters to our action block.
		   */
		def apply[A](bodyParser: BodyParser[A])(block: (Request[A], ServerUser) => Result): Action[A] = async(bodyParser){ (request:Request[A], user:ServerUser) =>
	      block(request, user) match {
		      case simple: SimpleResult => Future.successful(simple)
		      case async: AsyncResult => async.unflatten
		    }
		}
		
				  /**
		   * This function is needed so that we can modify the input parameters to our action block.
		   */
		def apply(block: (Request[AnyContent], ServerUser) => Result): Action[AnyContent] = async(play.api.mvc.BodyParsers.parse.anyContent){ (request:Request[AnyContent], user:ServerUser) =>
	      block(request, user) match {
		      case simple: SimpleResult => Future.successful(simple)
		      case async: AsyncResult => async.unflatten
		    }
		}
  
		def invokeBlock[A](request: Request[A], block: (Request[A], ServerUser) => Future[SimpleResult]) = 
		{
		    
			var body = request.body
			
			var user:Option[ServerUser] = helpers.Security.getUserInfo(request)
			    
			if(!user.isEmpty)
			{
				block(request, user.get)
			}
			else
			{
				scala.concurrent.Future(Results.Forbidden(views.html.error("You do not have permission to access this page. This could be do you being logged out.")))
			}
		    
		}
	}
  