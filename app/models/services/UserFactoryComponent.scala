package models
package services
import models.objects._

trait UserFactoryComponent {

  def userFactory: UserFactory

  trait UserFactory {

    def create( email: String, password: String, firstName: String, lastName: String, role: Role): User 
  
  }
}