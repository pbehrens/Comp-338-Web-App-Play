package models
package services
import models.objects._



trait UserRepositoryComponent {
  
  def userRepository: UserRepository
  
  trait UserRepository {
    //changed the addResource definition to match the default implementation
    def getAllUsers: List[User]
    def removeAllUsers
    def getUsers(lastName: String): List[User]
    def getUsers(lastName: String, firstName: String): List[User]
    def getUser(email: String): Option[User]
    def addUser(userID: Int, email: String, firstName: String, lastName: String, role: Role): Option[User]
    def requestMembership(requestee: User): Unit
    def addMembership(newMember: User, verifyingStaff: User): Boolean
    def removeMembership(oldMember: User): Boolean
    def getMembershipRequests: List[User]
    def getResources(): List[Resource]
    def getResource(name: String): Option[Resource]
    def getResource(resourceID: Int): Option[Resource]
    def setResourceOwner(resourceID: Int, member: User, staff: User): Boolean
    def removeResourceOwner(resourceID: Int, staff: User): Boolean
    def getNonReservedResources(): List[Resource]
    def addResource(resourceID: Int, name: String, description: String, staffer: User): Option[Resource]
    def removeResource(oldResource: Resource): Boolean
    def addReservation(requester: User, itemID: Int): Option[Reservation]
    def getReservations(user: User): List[Reservation]
    def getReservations: List[Reservation]
    def removeReservation(oldReservation: Reservation): Boolean
    
    
  }
}