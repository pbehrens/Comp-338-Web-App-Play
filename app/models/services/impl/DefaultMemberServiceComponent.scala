package models
package services.impl

import models.services._
import models.objects._

trait DefaultMemberServiceComponent extends MemberServiceComponent {
  this: UserRepositoryComponent =>
  
//  def memberService = new DefaultMemberService
  
  class DefaultMemberService extends MemberService {
    def reserveResource(requester: User, itemID: Int) = userRepository.addReservation(requester, itemID)
    def viewReservations(user: User) = userRepository.getReservations(user)
    def removeReservation(noLongerNeeded: Reservation) = userRepository.removeReservation(noLongerNeeded)
    def getAvailableResources = userRepository.getNonReservedResources()
    def getAllResources: List[Resource] = userRepository.getResources()

  }
}