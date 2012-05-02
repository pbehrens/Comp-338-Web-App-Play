package models
package services
 
import models.objects._

trait StaffServiceComponent {
  
  def staffService: StaffService
  
  trait StaffService{
    //added user to create resource
    def createResource(name: String, description: String, staffer: User): Option[Resource]
    def removeResource(oldResource: Resource): Boolean
    def viewAllResources(): List[Resource]
    def checkIn(returnedResource: Resource): Unit
    def checkOut(returnedResource: Resource): Boolean
    def viewReservations(member: User): List[Reservation]
    def viewReservations(resource: Resource): List[Reservation]
    def viewReservations(): List[Reservation]
    def deleteReservation(oldReservation: Reservation): Boolean
    def deleteReservations(oldReservations: List[Reservation]): Boolean
    def viewMembers():List[User]
    def getResource(id: Int): Option[Resource]
    def addMember(email: String, password: String, firstName: String, lastName: String, role: Role): Option[User]
  }
  
}