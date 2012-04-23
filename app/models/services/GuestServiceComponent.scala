package domain
package services

import domain.objects._

trait GuestServiceComponent {
  
  def guestService: GuestService
  
  trait GuestService{
    def getAvailableResources: List[Resource]
    def requestMembership(requestee: User): Unit
  }
  
}