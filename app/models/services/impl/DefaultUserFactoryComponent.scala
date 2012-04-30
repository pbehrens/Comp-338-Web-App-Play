package models
package services.impl

import models.services._
import models.objects._
import controllers.Registry

trait DefaultUserFactoryComponent extends UserFactoryComponent {

  object userFactory extends UserFactory {

    sealed case class DefaultUser(userID: Int, email: String, password: String, firstName: String, lastName: String, role: Role) extends User

    def iDNumber(): Int = Registry.userRepository.getLargestUserID +1
    def create(userID: Int, email: String, password: String, firstName: String, lastName: String, role: Role) = DefaultUser(userID, email, password, firstName, lastName, role)
  }
}