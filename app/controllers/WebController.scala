package controllers


import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import domain.services._
import domain.objects._

trait WebController extends Controller {
	  this: UserRepositoryComponent with UserFactoryComponent with ReservationFactoryComponent with ResourceFactoryComponent=>

    
	def index
	
	def guest 
	
	def member
	
	def staff 
	
	def viewReservations 
	
	def deleteReservation(id: Int)
	
	def addReservation(resourceReserved: Resource, member: User, time: Date ) 
	
	def editReservation(reservationID: Int) 
	
	def viewItems
	
	def deleteItem(id: String) 
	
	def editItem(id: String) 
	
	def addItem(name: String, description: String)
	
	def viewMembers()
	
	def deleteMemeber(id: String)
	
	def editMember(id: String)
	
	def addMember()
}