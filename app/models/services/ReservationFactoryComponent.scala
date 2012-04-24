package models
package services
 
import models.objects._
import java.util.Date

trait ReservationFactoryComponent {

  def reservationFactory: ReservationFactory

  trait ReservationFactory {

    def create(wantedResource: Resource, requester: User): Reservation 
  
  }
}