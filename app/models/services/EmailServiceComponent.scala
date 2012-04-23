package domain
package services

import domain.objects._

trait EmailServiceComponent {
  
  def emailService: EmailService
  
  trait EmailService {
	//TODO Provide Some Implementation here
  }
  
}


