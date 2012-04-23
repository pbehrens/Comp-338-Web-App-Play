package domain
package objects

trait Guest {
  def requestMembership(): Void
  def viewReservations(): String

}