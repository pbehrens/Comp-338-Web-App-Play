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
	this: UserRepositoryComponent with UserFactoryComponent with AuthenticationServiceComponent=>
	
   
//===========Authentication================//
  

  

  //+++++++++++++++Main Navigation Items++++++++++++++++++++++
   
  def index = Action {
    
	  Ok(views.html.index("")) 
	}
	
	def guest = Action{
	  //get list of all items
	  Ok(views.html.guest("")) 
	}
	
	def member = Action{
	  //get list of available items and return list to be made into html list
	  Ok(views.html.member("")) 
	}
	
	def staff = Action{
	  //main page for editing items and reservations
	  Ok(views.html.staffpublic(""))  
	}
}

/**
 * Provide security features
 */
trait Secured extends AuthenticationServiceComponent {

  def username(request: RequestHeader) = request.session.get(Security.username)

  def onUnauthorized(request: RequestHeader) = Results.Redirect(routes.Application.login)

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
}

object Application extends ApplicationController with DefaultUserRepositoryComponent
  with DefaultUserFactoryComponent
  with DefaultResourceFactoryComponent
  with DefaultReservationFactoryComponent
  with DefaultAuthenticationServiceComponent