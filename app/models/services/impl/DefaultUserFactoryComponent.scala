package models
package services.impl

import models.services._
import models.objects._

trait DefaultUserFactoryComponent extends UserFactoryComponent {

  object userFactory extends UserFactory {

    sealed case class DefaultUser(userID: Int, email: String, firstName: String, lastName: String, role: Role) extends User

    def create(userID: Int, email: String, firstName: String, lastName: String, role: Role) = DefaultUser(userID, email, firstName, lastName, role)
  }
}