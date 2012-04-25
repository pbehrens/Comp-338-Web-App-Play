package models
package services



import models.objects._

trait EmailServiceComponent {
  
  def emailService: EmailService
  
  trait EmailService {
    
    def reservationNotify(member: User, resource: Resource, reservation: Reservation): Unit
    
    def expirationNotify(member: User, resource: Resource, reservation: Reservation): Unit
	//TODO Provide Some Implementation here
  }
  
}


