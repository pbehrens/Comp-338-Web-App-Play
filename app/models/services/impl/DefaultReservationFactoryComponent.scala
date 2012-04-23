package domain
package services.impl

import domain.services._
import domain.objects._
import java.util.Date

trait DefaultReservationFactoryComponent extends ReservationFactoryComponent {

  object reservationFactory extends ReservationFactory {

    sealed case class DefaultReservation(resourceReserved: Resource, currentmember: User, time: Date) extends Reservation

    def create(wantedResource: Resource, requester: User) = new DefaultReservation(wantedResource, requester, new Date())
  }
}