package models
package services.impl

import models.services._
import models.objects._


trait DefaultEmailServiceComponent extends EmailServiceComponent {
//  this: UserRepositoryComponent =>
   
//  def emailService = new DefaultEmailService
  
  class DefaultEmailService extends EmailService{
	 //TODO provide implementation of currently nonexistent EmailService Functions
    
    
    def reservationNotify(member: User, resource: Resource, reservation: Reservation): Unit={}
    
    def expirationNotify(member: User, resource: Resource, reservation: Reservation): Unit ={}
  }
}