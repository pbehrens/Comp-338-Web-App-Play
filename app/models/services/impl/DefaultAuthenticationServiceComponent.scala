package models.services.impl

import models.services.AuthenticationServiceComponent
import models.services._
import models.objects.User
import models.objects.NullUser

trait DefaultAuthenticationServiceComponent extends AuthenticationServiceComponent{
  this: UserRepositoryComponent =>
    
//  def authenticationService = new DefaultAuthenticationService
  
  class DefaultAuthenticationService extends AuthenticationService {
   
    def authenticate(email: String, password: String):Option[User] = {
      val user = userRepository.getUser(email)
      if(user.getOrElse(NullUser).password != password)
        return None
      user
    }
    
    def findUser(email: String):Option[User] = {
    	userRepository.getUser(email)
    }
      
    
    
  }
  
  
  
}