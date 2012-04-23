package domain
package services

import domain.objects._

trait ResourceFactoryComponent {

  def resourceFactory: ResourceFactory

  trait ResourceFactory {

    def create(resourceID: Int, name: String, description: String): Resource 
  }
}