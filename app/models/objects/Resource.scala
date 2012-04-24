package models
package objects

trait Resource {
  val resourceID: Int
  val name: String
  val description: String
  var currentMember: Option[User]
  var reservations: List[Reservation]
  
   def ==(check: Resource): Boolean = {
    (this.resourceID == check.resourceID) && (this.name == check.name) && (this.description == check.description)
  }
}