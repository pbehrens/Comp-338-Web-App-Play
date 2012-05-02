package models
package services
 

import models.objects._

trait ResourceFactoryComponent {

  def resourceFactory: ResourceFactory

  trait ResourceFactory {
    def create(resourceID: Int, name: String, description: String): Resource 
  }
}