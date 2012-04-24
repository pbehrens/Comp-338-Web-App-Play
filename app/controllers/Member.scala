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
 
  
  //================Forms===================
  val reservationForm = Form(
    	tuple(
    		"time" -> text,
    		"resource" -> text,
    		"member" -> text
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

	}
	
	def deleteReservation(id: Int) = Action{

	}
	
	def makeReservation() = Action{

	}
		
	def viewItems = Action{
	
	}
	
	def editUserInfo = Action{
	}
}

object Member extends MemberController