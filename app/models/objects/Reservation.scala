package models
package objects

import java.util.Date

trait Reservation {
  val resourceReserved: Resource
  val currentmember: User
  val time: Date

  def ==(compareReservation: Reservation): Boolean ={
  	(this.resourceReserved.resourceID == compareReservation.resourceReserved.resourceID) &&
  	(this.currentmember.email == compareReservation.currentmember.email) &&
  	(this.time.equals(compareReservation.time)) 
  }

}