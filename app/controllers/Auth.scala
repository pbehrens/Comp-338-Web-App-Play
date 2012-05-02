package controllers


import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.objects._
import models.services._
import models.services.impl._
import views._

trait AuthController extends Controller {
	this: AuthenticationServiceComponent=>
	  
	val authenticationService = Registry.authenticationService.asInstanceOf[AuthenticationService]
	  
val form = Form(
    tuple(
      "email" -> text,
      "password" -> text
    ) verifying ("Invalid email or password", result => result match {
      case (email, password) => authenticationService.authenticate(email, password).isDefined
    })
  )

  /**
   * Login page.
   */
  def login = Action { implicit request =>
    Ok(views.html.login(form))
  }

  /**
   * Handle login form submission.
   */
  def authenticate = Action { implicit request =>
    form.bindFromRequest.fold(
      formWithErrors => BadRequest(views.html.login(formWithErrors)),
      user => Redirect(routes.Application.index).withSession("email" -> user._1)
    )
  }

  /**
   * Logout and clean the session.
   */
  def logout = Action {
    Redirect(routes.Auth.login).withNewSession.flashing(
      "success" -> "You've been logged out"
    )
  }
}

trait Secured extends AuthenticationServiceComponent {

  def username(request: RequestHeader) = request.session.get("email")

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
  
    /**
   * Check if the connected user is a member of this role.
//   */
//  def IsMemberOf(project: Long)(f: => String => Request[AnyContent] => Result) = IsAuthenticated { user => request =>
//    if(Project.isMember(project, user)) {
//      f(user)(request)
//    } else {
//      Results.Forbidden
//    }
//  }
  
  /** 
   * Action for authenticated users.
   */

//  def IsAuthenticated(f: => User => Request[AnyContent] => Result) = {
//    implicit request =>
//  		session.get("email").map { email =>
//  		  	val user = authenticationService.findUser(email)
//  		  	Ok(views.html.index(user.get.email)) 
//  			}.getOrElse {"not authenticated")) 
//  
//  			}
//    
//    
//  }
//  
//  
//    
//    
//    Security.Authenticated(username, onUnauthorized) { user =>
//    Action(request => f(user)(request))
//  }

}

object Auth extends AuthController with AuthenticationServiceComponent