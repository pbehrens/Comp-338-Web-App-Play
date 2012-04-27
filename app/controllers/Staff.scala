package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models._
import models.objects._
import models.services._
import models.services.impl._



trait StaffController  {
	this: AuthenticationServiceComponent with StaffServiceComponent =>
	  
  val staffService: StaffService
  val authenticationService: AuthenticationService
  
  object Staff extends StaffController with Controller with Secured with AuthenticationServiceComponent with StaffServiceComponent {
    override val staffService = Registry.staffService
    override val authenticationService = Registry.authenticationService
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
  
  
//++++++++++++++++Actions+++++++++++++++++++++
    
    
    def viewReservations = Action{
	  //get list of all reservations and return as argument fo
	  val reservs = Registry.userRepository.getReservations
	  Ok(views.html.staff.viewReservations(""))
	}
	
	def deleteReservation(id: Int) = Action{
	  //delete reservation
		Redirect(routes.Staff.viewReservations)
	}
	
	def addReservation() = Action{
	  //add reservation to depot generate id for it and add to repo 
	  
	Redirect(routes.Staff.viewReservations)
	}
	
//	def editReservation(id: Int) = Action{
//	  //pull up reservation by ID and send info to view so forms can be populated then use addReservation to add it into the repo
//	  //not sure if you want to delete the reservation then just create a new one
//	  
//		Ok(views.html.editReservation(""))	
//	}
	
	def viewItems = Action{
	 //get list of all items and return in Ok method
	  Ok(views.html.staff.viewItems(""))
	}
	
	def deleteItem(id: Int) = Action{
	  //grab item from repo and remove it then redirect
	Redirect(routes.Staff.viewItems)
	}
	
	def editItem(id: Int) = Action{
	  //TODO create form for edting item and grab item by id number
	  
	  Ok(views.html.staff.editItem(""))
	}
	
	def addItem() = Action{
	 //add reservation to depot generate id for it and add to repo
	  
	Redirect(routes.Staff.viewItems)
	}
	
	def viewMembers() = Action{
	  //grab list of members from user repo
	  Ok(views.html.staff.viewMembers(""))
	}
	
	def deleteMember(id: Int) = Action{
	  //grab member from repo and remove it then redirect
	Redirect(routes.Staff.viewMembers)
	}
	
	def editMember(id: Int) = Action{
	  //TODO create form for edting member and grab member by id number
	  
	  Ok(views.html.staff.viewItems(""))
	}
	
	def addMember() = Action{ 
	Redirect(routes.Staff.viewMembers)
	}	 
	
	}
}

