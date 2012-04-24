package controllers


import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models._
import models.objects._
import models.services._
import models.services.impl._





trait ApplicationController extends Controller {
	this: ReservationFactoryComponent with ResourceFactoryComponent with UserFactoryComponent =>

  
    val itemForm = Form(
    tuple("name" -> nonEmptyText,
    "description" -> nonEmptyText)
    )

  
    
    val reservationForm = Form(
    	tuple(
    		"time" -> text,
    		"resource" -> text
    		)
    )
    
	
    def index = Action {
      
      var u = Role(true,true,true,true)
      val resource1 = resourceFactory.create(54,"peter", "peter")
      
	  Ok(views.html.index("", resource1.name))
	  
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
	  Ok(views.html.viewReservations(""))
	}
	
	def deleteReservation(id: Int) = Action{
	  //delete reservation
		Redirect(routes.Application.viewReservations)
	}
	def addReservation(resourceReserved: Resource, member: User) = Action{
	  //add reservation to depot generate id for it and add to repo
	  
	Redirect(routes.Application.viewReservations)
	}
	
	def editReservation(reservationID: Int) = Action{
	  //pull up reservation by ID and send info to view so forms can be populated then use addReservation to add it into the repo
	  //not sure if you want to delete the reservation then just create a new one
	  
		Ok(views.html.editReservation("", itemForm))	
	}
	
	def viewItems = Action{
	 //get list of all items and return in Ok method
	  Ok(views.html.viewItems(""))
	}
	
	def deleteItem(id: String) = Action{
	  //grab item from repo and remove it then redirect
	Redirect(routes.Application.viewItems)
	
	}
	
	def editItem(id: String) = Action{
	  //TODO create form for edting item and grab item by id number
	  
	  Ok(views.html.viewItems(""))
	}
	def addItem(name: String, description: String) = Action{
	 //add reservation to depot generate id for it and add to repo
	  
	Redirect(routes.Application.viewItems)
	}
	
	def viewMembers() = Action{
	  //grab list of members from user repo
	  
	  Ok(views.html.viewMembers(""))

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


object Application extends ApplicationController with DefaultReservationFactoryComponent with
DefaultResourceFactoryComponent with DefaultUserFactoryComponent
