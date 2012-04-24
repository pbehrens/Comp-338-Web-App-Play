package models
package services.impl

import models.services._
import models.objects._


trait DefaultGuestServiceComponent extends GuestServiceComponent {
  this: UserRepositoryComponent =>
   
  def guestService = new DefaultGuestService
  
  class DefaultGuestService extends GuestService{
    def getAvailableResources = userRepository.getNonReservedResources()
    def requestMembership(requestee: User) = userRepository.requestMembership(requestee)
  }
}