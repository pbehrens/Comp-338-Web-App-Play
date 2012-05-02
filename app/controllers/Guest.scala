package controllers


import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models._
import models.objects._
import models.services._
import models.services.impl._





trait GuestController extends Controller with Secured {
	this: AuthenticationServiceComponent with GuestServiceComponent =>
	
	val authenticationService = Registry.authenticationService.asInstanceOf[AuthenticationService]
	val guestService = Registry.guestService.asInstanceOf[GuestService]
			
//===========Authentication================//
  

  

  //+++++++++++++++Main Navigation Items++++++++++++++++++++++
	def index = Action{
	  //get list of all items
	  val items = guestService.getAvailableResources
	  
	  Ok(views.html.guest.viewItems("", items)) 
	}

}



object Guest extends GuestController
  with AuthenticationServiceComponent
  with GuestServiceComponent