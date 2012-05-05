package controllers


import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models._
import models.objects._
import models.services._
import models.services.impl._





trait ApplicationController extends Controller with Secured {
	this: AuthenticationServiceComponent =>
	
	val authenticationService = Registry.authenticationService.asInstanceOf[AuthenticationService]
   
//===========Authentication================//
  

  

  //+++++++++++++++Main Navigation Items++++++++++++++++++++++
   
  def index = Action {implicit request =>
  		session.get("email").map { email =>
  		  	val user = authenticationService.findUser(email)
  		  	Ok(views.html.index(user.get.email)) 
  			}.getOrElse {
  			Ok(views.html.index("Not Logged In"))
  			}
	}
	
	def error = Action{
	  Ok(views.html.error(""))
	}
	
	def member = Action{
	  //get list of available items and return list to be made into html list
	  Ok(views.html.members.index("")) 
	}
	
}

/**
 * Provide security features
 */
/*trait Secured extends AuthenticationServiceComponent {

  def username(request: RequestHeader) = request.session.get(Security.username)

  def onUnauthorized(request: RequestHeader) = Results.Redirect(routes.Auth.login)

  def withAuth(f: => String => Request[AnyContent] => Result) = {
    Security.Authenticated(username, onUnauthorized) { user =>
      Action(request => f(user)(request))
    }
  }

  /**
   * This method shows how you could wrap the withAuth method to also fetch your user
   * You will need to implement UserDAO.findOneByUsername
   */
  def withUser(f: User => Request[AnyContent] => Result) = withAuth { username => implicit request =>
    authenticationService.findUser(username).map { user =>
      f(user)(request)
    }.getOrElse(onUnauthorized(request))
  }
}*/

object Application extends ApplicationController
  with AuthenticationServiceComponent