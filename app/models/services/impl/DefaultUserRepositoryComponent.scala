package models
package services.impl

import models.services._
import models.objects._

import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet


trait DefaultUserRepositoryComponent extends UserRepositoryComponent {
	this:UserFactoryComponent with ReservationFactoryComponent with ResourceFactoryComponent=>

  /**
   * An instance that gets initialized only if used.
   */
  lazy val instance = new DefaultUserRepository
  override val userRepository = instance

  class DefaultUserRepository extends UserRepository {
   
    val map = HashMap[String,User]()
    val resourceMap = HashMap[Int, Resource]()
    val memberRequests =  HashSet[User]()
    

		/**
		 * A utility function used to verify that the user passed in matches records.
		 * If the user doesn't exist or match, you get a NullUser
		 * If the user exists and matches, you get the user back out
		 */
    private def checkUser(check: User):User = this.map.getOrElse(check.email, NullUser)
    
    def getAllUsers: List[User] = this.map.values.toList
    
    def removeAllUsers = {map.clear}

    def getUsers(lastName: String): List[User] = this.map.values.filter(_.lastName == lastName).toList
    
    def getUsers(lastName: String, firstName: String): List[User]={
    	this.getUsers(lastName).filter(_.firstName == firstName).toList
    }
    
    def getUser(email: String): Option[User] = this.map.get(email)
    
    def addUser(userID: Int, email: String, firstName: String, lastName: String, role: Role): Option[User] = {
    	if(!this.map.contains(email)){
    		val user = userFactory.create(userID, email, firstName, lastName, role)
    		this.map += email -> user
    	    return Some(user)
    	}
    	None
    }
    
    def requestMembership(requestee: User): Unit={
    	if(!requestee.role.isMember){
    		if(requestee == checkUser(requestee)){  //Verify user matches record
    			memberRequests += requestee
    		}
    	}

    }
    
    def addMembership(newMember: User, verifyingStaff: User): Boolean = {
	  	if(memberRequests.contains(newMember) && checkUser(verifyingStaff).role.isStaff){
	  		memberRequests -= newMember
	  		checkUser(newMember).role.makeMember
	  		return true
	  	}
	  	false
    }
    
    def removeMembership(oldMember: User): Boolean ={
      checkUser(oldMember).role.removeMember
      true
    }
    
    def getMembershipRequests: List[User]={
      return memberRequests.toList
    }
    
    def getResources(): List[Resource] = resourceMap.values.toList
    
    def getResource(name: String): Option[Resource] = getResources().find(_.name == name)
    
    def getResource(resourceID: Int): Option[Resource] = resourceMap.get(resourceID)
    
    def setResourceOwner(resourceID: Int, member: User, staff: User): Boolean = {
      val resourceOption = resourceMap.get(resourceID)
      if (member.role.isMember && staff.role.isStaff && !resourceOption.isEmpty){
        val resource = resourceOption.get
        resource.currentMember = Some(member)
        resourceMap.put(resourceID, resource)
        return true
      }
      return false
    }
    
    def removeResourceOwner(resourceID: Int, staff: User): Boolean= {
      val resourceOption = resourceMap.get(resourceID)
      if (staff.role.isStaff && !resourceOption.isEmpty){
        val resource = resourceOption.get
        resource.currentMember = None
        resourceMap.put(resourceID, resource)
        return true
      }
      return false
    }
    
    def getNonReservedResources(): List[Resource] = getResources.filter(_.currentMember.isEmpty)
    
    def addResource(resourceID: Int, name: String, description: String, staffer: User): Option[Resource] = {
      //fixed what seemed to be a missing parentheses here...needs to be double checked
    	if(staffer.role.isStaff && !resourceMap.contains(resourceID) && this.getResource(name) == None){
    	    val newResource = resourceFactory.create(resourceID, name, description)
    		resourceMap.put(resourceID, newResource)
    		return Some(newResource)
    	}
    	None
    }
    
    
    
    def removeResource(oldResource: Resource): Boolean = {
    	!resourceMap.remove(oldResource.resourceID).isEmpty
    }
    
    def addReservation(requester: User, itemID: Int): Option[Reservation] = {
      //println(resourceMap.get(itemID)
      //TODO  CAUSES STACK OVERFLOW
    	val resourceOption = resourceMap.get(itemID)
    	if(!resourceOption.isEmpty && requester.role.isMember){
    	  val resource = resourceOption.get
    	  val reservation = reservationFactory.create(resource, requester)
    		resource.reservations = resource.reservations :+ reservation
    		resourceMap.put(resource.resourceID, resource)
    		return Some(reservation)
    	}
    	None
    }
    
    def getReservations(user: User): List[Reservation] = resourceMap.values.map(_.reservations).reduceLeft((a, b) => a++b).filter(_.currentmember == user)
    
    def getReservations: List[Reservation] = resourceMap.values.map(_.reservations).reduceLeft((a, b) => a++b)

    
    def removeReservation(oldReservation: Reservation): Boolean = {
    	val resourceOption = resourceMap.get(oldReservation.resourceReserved.resourceID)
    	if(!resourceOption.isEmpty){
    		var resource = resourceOption.get
    		resource.reservations = resource.reservations.filterNot(_ == oldReservation)
    		resourceMap.put(resource.resourceID, resource)
    		return true
    	}
    	false
    }

  }
  
}