package domain
package services

import domain.objects._

trait UserFactoryComponent {

  def userFactory: UserFactory

  trait UserFactory {

    def create(userID: Int, email: String, firstName: String, lastName: String, role: Role): User 
  
  }
}