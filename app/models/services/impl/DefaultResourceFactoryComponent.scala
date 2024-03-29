package models
package services.impl

import models.objects._
import models.services.ResourceFactoryComponent

trait DefaultResourceFactoryComponent extends ResourceFactoryComponent {

  object resourceFactory extends ResourceFactory {

    sealed case class DefaultResource(resourceID: Int, name: String, description: String, var currentMember: Option[User], var reservations: List[Reservation]) extends Resource
    
    def create(resourceID: Int, name: String, description: String) = DefaultResource(resourceID, name, description, None, List())
  }
}