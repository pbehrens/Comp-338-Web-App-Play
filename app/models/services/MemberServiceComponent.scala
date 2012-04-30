package models
package services
 

import models.objects._

trait MemberServiceComponent {
  
  def memberService: MemberService
  
  trait MemberService {
    def reserveResource(requester: User, itemID: Int): Unit
    def viewReservations(member: User): List[Reservation]
    def removeReservation(noLongerNeeded: Reservation): Boolean
    def getAvailableResources: List[Resource]

  }
  
}