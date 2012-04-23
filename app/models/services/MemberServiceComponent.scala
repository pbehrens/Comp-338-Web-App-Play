package domain
package services

import domain.objects._

trait MemberServiceComponent {
  
  def memberService: MemberService
  
  trait MemberService {
    def reserveResource(requester: User, itemID: Int): Unit
    def viewReservations(member: User): List[Reservation]
    def removeReservation(noLongerNeeded: Reservation): Boolean
  }
  
}