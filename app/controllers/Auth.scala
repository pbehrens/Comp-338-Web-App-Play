package controllers


import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
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

object Auth extends AuthController with AuthenticationServiceComponent