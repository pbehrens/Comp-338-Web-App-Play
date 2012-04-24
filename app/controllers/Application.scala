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
//	this: UserRepositoryComponent with UserFactoryComponent =>
	
  
  
	//===========Authentication
val loginForm = Form(
    tuple(
      "email" -> text,
      "password" -> text
    ) verifying ("Invalid email or password", result => result match {
      case (email, password) => User.authenticate(email, password).isDefined
    })
  )

  /**
   * Login page.
   */
  def login = Action { implicit request =>
    Ok(html.login(loginForm))
  }

  /**
   * Handle login form submission.
   */
  def authenticate = Action { implicit request =>
    loginForm.bindFromRequest.fold(
      formWithErrors => BadRequest(html.login(formWithErrors)),
      user => Redirect(routes.Projects.index).withSession("email" -> user._1)
    )
  }

  /**
   * Logout and clean the session.
   */
  def logout = Action {
    Redirect(routes.Application.login).withNewSession.flashing(
      "success" -> "You've been logged out"
    )
  }
  
  //================Forms===================
  val reservationForm = Form(
    	tuple(
    		"time" -> text,
    		"resource" -> text,
    		"member" -> text
    		)
    )
    
  val itemForm = Form(
    tuple("name" -> nonEmptyText,
    "description" -> nonEmptyText)
    )
    
    val memberEditForm = Form(
    tuple("email" -> nonEmptyText,
    	  "firstName" -> nonEmptyText,
    	  "lastName" -> nonEmptyText,
    	  "role" -> nonEmptyText)  
    )
  
  
  
  //+++++++++++++++Main Web Things
  
    

    
    
    def index = Action {
	  Ok(views.html.index("", "test")) 
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
	  Ok(views.staff.html.staff(""))  
	}
	
	def viewReservations = Action{
	  //get list of all reservations and return as argument for 
	  Ok(views.staff.html.viewReservations(""))
	}
	
	def deleteReservation(id: Int) = Action{
	  //delete reservation
		Redirect(routes.Application.viewReservations)
	}
	def addReservation() = Action{
	  //add reservation to depot generate id for it and add to repo 
	Redirect(routes.Application.viewReservations)
	}
	
//	def editReservation(id: Int) = Action{
//	  //pull up reservation by ID and send info to view so forms can be populated then use addReservation to add it into the repo
//	  //not sure if you want to delete the reservation then just create a new one
//	  
//		Ok(views.html.editReservation(""))	
//	}
	
	def viewItems = Action{
	 //get list of all items and return in Ok method
	  Ok(views.staff.html.viewItems(""))
	}
	
	def deleteItem(id: Int) = Action{
	  //grab item from repo and remove it then redirect
	Redirect(routes.Application.viewItems)
	}
	
	def editItem(id: Int) = Action{
	  //TODO create form for edting item and grab item by id number
	  
	  Ok(views.html.editItem(""))
	}
	
	def addItem() = Action{
	 //add reservation to depot generate id for it and add to repo
	  
	Redirect(routes.Application.viewItems)
	}
	
	def viewMembers() = Action{
	  //grab list of members from user repo
	  Ok(views.html.viewMembers(""))
	}
	
	def deleteMember(id: Int) = Action{
	  //grab member from repo and remove it then redirect
	Redirect(routes.Application.viewMembers)
	}
	
	def editMember(id: Int) = Action{
	  //TODO create form for edting member and grab member by id number
	  
	  Ok(views.html.viewItems(""))
	}
	
	def addMember() = Action{ 
	Redirect(routes.Application.viewMembers)
	}	
}

/**
 * Provide security features
 */
trait Secured {

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
    UserDAO.findOneByUsername(username).map { user =>
      f(user)(request)
    }.getOrElse(onUnauthorized(request))
  }
}

object Application extends ApplicationController