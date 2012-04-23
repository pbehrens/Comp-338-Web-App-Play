package controllers


import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import controllers.WebController




trait App{
//  this: UserRepositoryComponent with UserFactoryComponent with ReservationFactoryComponent with ResourceFactoryComponent=>

}



object Application extends WebController{

    val itemForm = Form(
    tuple("name" -> nonEmptyText,
    "description" -> nonEmptyText)
    )
    
    val loginForm = Form(
    		tuple(
    			"email" -> nonEmptyText,
    			"password" -> nonEmptyText
    		) verifying("Invalid user name or password", { 
    		case (e, p) => User.authenticate(e,p).isDefined }))
  
    
    val reservationForm = Form(
    	tuple(
    		"time" -> text,
    		"resource" -> text
    		)
    )
    
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
	  Ok(views.html.staff("")) 
	  
	}
	
	def viewReservations = Action{
	  //get list of all reservations and return as argument for 
	  Ok(views.html.viewReservations("", reservations))
	}
	
	def deleteReservation(id: Int) = Action{
	  //delete reservation
		Redirect(routes.Application.viewReservations)
	}
	def addReservation(resourceReserved: Resource, member: User, time: Date ) = Action{
	  //add reservation to depot generate id for it and add to repo
	  
	Redirect(routes.Application.viewReservations)
	}
	
	def editReservation(reservationID: Int) = Action{
	  //pull up reservation by ID and send info to view so forms can be populated then use addReservation to add it into the repo
	  //not sure if you want to delete the reservation then just create a new one
	  
		Ok(views.html.editReservation(user,date,reservation))	
	}
	
	def viewItems = Action{
	 //get list of all items and return in Ok method
	  Ok(views.html.viewItems("", itemForm))
	}
	
	def deleteItem(id: String) = Action{
	  //grab item from repo and remove it then redirect
	Redirect(routes.Application.viewItems)
	
	}
	
	def editItem(id: String) = Action{
	  //TODO create form for edting item and grab item by id number
	  
	  Ok(views.html.viewItems("", item))
	}
	def addItem(name: String, description: String) = Action{
	 //add reservation to depot generate id for it and add to repo
	  
	Redirect(routes.Application.viewItems)
	}
	
	def viewMembers() = Action{
	  //grab list of members from user repo
	  
	  Ok(views.html.viewMembers("", userList))

	}
	
	def deleteMemeber(id: String) = Action{
	  //grab member from repo and remove it then redirect
	Redirect(routes.Application.viewMembers)
	
	}
	
	def editMember(id: String) = Action{
	  //TODO create form for edting member and grab member by id number
	  
	  Ok(views.html.viewItems(""))
	}
	
	def addMember() = Action{
	  
	Redirect(routes.Application.viewMembers("succes or not success string"))
	}
	
}