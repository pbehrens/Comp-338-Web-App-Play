package models
package objects

trait Staff {
  def approveMember(member: Member): Boolean
  def checkInResource(resource: Resource): Void
  def deleteResource(resource: Resource): Void
  def checkOut(member: Member, item: Resource): Void

}