package models
package objects

trait Member {
  val items: Array[Guest]
  var reservations: Array[Reservation]
  
  def reserveSpot(spot: Reservation): Boolean
  def deleteCurrentReservation(spot: Reservation): Boolean
  def checkOutResource(item: Resource): Void

}