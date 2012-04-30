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
    
  val memberService = Registry.memberService.asInstanceOf[MemberService]
  val authenticationService = Registry.authenticationService.asInstanceOf[AuthenticationService]
 
  
  //================Forms===================
  val reservationForm = Form(
    	tuple(
    		"time" -> text,
    		"resource" -> text
    		)
    )
     
    val memberEditForm = Form(
    tuple("email" -> nonEmptyText,
    	  "password" -> nonEmptyText,
    	  "firstName" -> nonEmptyText,
    	  "lastName" -> nonEmptyText
    	   )  
    )
  
  
//++++++++++++++++Actions+++++++++++++++++++++
    def viewReservations = Action{

    Ok(views.html.members.index(""))//TODO add style to page

	}
	
	def deleteReservation() = Action{ //TODO make sure everything works with reservation ID

	Ok(views.html.index(""))

	}
	
	def makeReservation() = Action{//TODO implement
    Ok(views.html.index(""))

	}
		
	def viewItems = Action{ //TODO implement
	  
	val items = memberService.getAvailableResources

    Ok(views.html.members.viewItems("", items))
	
	}
	
	def editUserInfo = Action{
    Ok(views.html.index(""))
	
	}
}

object Member extends MemberController with MemberServiceComponent with AuthenticationServiceComponent
