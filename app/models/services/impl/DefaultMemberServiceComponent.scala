package domain
package services.impl

import domain.services._
import domain.objects._

trait DefaultMemberServiceComponent extends MemberServiceComponent {
  this: UserRepositoryComponent =>
  
  def memberService = new DefaultMemberService
  
  class DefaultMemberService extends MemberService {
    def reserveResource(requester: User, itemID: Int) = userRepository.addReservation(requester, itemID)
    def viewReservations(user: User) = userRepository.getReservations(user)
    def removeReservation(noLongerNeeded: Reservation) = userRepository.removeReservation(noLongerNeeded)
  }
}