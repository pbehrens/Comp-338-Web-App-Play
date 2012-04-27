package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models._
import models.objects._
import models.services._
import models.services.impl._



trait MemberController extends Controller with Secured {
  this: MemberServiceComponent with AuthenticationServiceComponent=>
    
  val memberService = Registry.memberService
  val authenticationService = Registry.authenticationService
 
  
  //================Forms===================
  val reservationForm = Form(
    	tuple(
    		"time" -> text,
    		"resource" -> text
    		)
    )
     
    val memberEditForm = Form(
    tuple("email" -> nonEmptyText,
    	  "firstName" -> nonEmptyText,
    	  "lastName" -> nonEmptyText,
    	  "role" -> nonEmptyText)  
    )
  
  
//++++++++++++++++Actions+++++++++++++++++++++
    def viewReservations = Action{

    Ok(views.html.index(""))

	}
	
	def deleteReservation() = Action{

	Ok(views.html.index(""))

	}
	
	def makeReservation() = Action{
    Ok(views.html.index(""))

	}
		
	def viewItems = Action{
    Ok(views.html.index(""))
	
	}
	
	def editUserInfo = Action{
    Ok(views.html.index(""))
	
	}
}

object Member extends MemberController with MemberServiceComponent with AuthenticationServiceComponent
