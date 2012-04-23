package domain
package services.impl

import domain.services._
import domain.objects._


trait DefaultEmailServiceComponent extends EmailServiceComponent {
//  this: UserRepositoryComponent =>
   
  def emailService = new DefaultEmailService
  
  class DefaultEmailService extends EmailService{
	 //TODO provide implementation of currently nonexistent EmailService Functions
  }
}