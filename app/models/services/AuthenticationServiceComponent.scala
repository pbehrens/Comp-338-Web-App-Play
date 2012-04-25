package models.services

import models.objects.User

trait AuthenticationServiceComponent {

  def authenticationService: AuthenticationService
  
  trait AuthenticationService {

    def authenticate(email: String, password: String):Option[User]
    def findUser(email: String):Option[User]
  }
}