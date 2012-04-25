package models
package services.impl

import models.services._
import models.objects._
import java.util.Date

trait DefaultReservationFactoryComponent extends ReservationFactoryComponent {

  object reservationFactory extends ReservationFactory {

    sealed case class DefaultReservation(reservationID: Int, resourceReserved: Resource, currentmember: User, time: Date) extends Reservation
    var newReservationNumber = 1
    def create(wantedResource: Resource, requester: User) = {
      newReservationNumber += 1
      new DefaultReservation(newReservationNumber, wantedResource, requester, new Date())
    }
  }
}