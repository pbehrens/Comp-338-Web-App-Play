package models
package services



import models.objects._

trait EmailServiceComponent {
  
  def emailService: EmailService
  
  trait EmailService {
	//TODO Provide Some Implementation here
  }
  
}


