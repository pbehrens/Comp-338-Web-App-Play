package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models._
import models.objects._
import models.services._
import models.services.impl._



trait StaffController {
	this: AuthenticationServiceComponent with StaffServiceComponent =>
	  
  val staffService: StaffService
  val authenticationService: AuthenticationService
}
  
  object Staff extends StaffController with Controller with Secured with AuthenticationServiceComponent with StaffServiceComponent {
    val staffService = Registry.staffService.asInstanceOf[StaffService]
    val authenticationService = Registry.authenticationService.asInstanceOf[AuthenticationService]
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
    
    val memberForm = Form(
    tuple("email" -> nonEmptyText,
    	  "password" -> nonEmptyText,
    	  "firstName" -> nonEmptyText,
    	  "lastName" -> nonEmptyText)
    )
  
  
//++++++++++++++++Actions+++++++++++++++++++++
    def splitView = Action{
      Ok(views.html.staff.index("", staffService.viewMembers(), staffService.viewAllResources(), memberForm, itemForm))
    }
    
    def viewReservations = Action{
	  //get list of all reservations and return as argument fo
      val reservs = staffService.viewReservations()
	  Ok(views.html.staff.viewReservations("", reservs))
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
	  val items = staffService.viewAllResources()
	  Ok(views.html.staff.viewItems("", items, itemForm))
	}
	
	def deleteItem(id: Int) = Action{
	  //grab item from repo and remove it then redirect
	  val item = staffService.getResource(id)
	  staffService.removeResource(item.get)
	Redirect(routes.Staff.splitView)
	}
	
	def editItem(id: Int) = Action{
	  //TODO create form for edting item and grab item by id number
	  
	  Ok(views.html.staff.editItem(""))
	}
	
	def addItem() = Action{
	 //add reservation to depot generate id for it and add to repo
	implicit request =>
    itemForm.bindFromRequest.fold(
      errors => BadRequest(views.html.error("")),
      value => {
        staffService.createResource(value._1,value._2, Registry.matt)
        Redirect(routes.Staff.splitView)
      }
    )
	  
	Redirect(routes.Staff.splitView)
	}
	
	def viewMembers() = Action{
	  //grab list of members from user repo
	  val members = staffService.viewMembers()
	  Ok(views.html.staff.viewMembers("", members, memberForm))
	}
	
	def deleteMember(id: Int) = Action{
	  //grab member from repo and remove it then redirect
	Redirect(routes.Staff.viewMembers)
	}
	
	def editMember(id: Int) = Action{
	  //TODO create form for edting member and grab member by id number
	  
	  Ok(views.html.staff.editMember(""))
	}
	
	def addMember() = Action{ 
	  	implicit request =>
    memberForm.bindFromRequest.fold(
      errors => BadRequest(views.html.error("")),
      value => {
        staffService.addMember(value._1, value._2, value._3, value._4, new Role(false, true, false, false))
       
        Redirect(routes.Staff.splitView)

      }
      
      

    )
	}	 
	
}

