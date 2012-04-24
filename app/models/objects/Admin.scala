package models
package objects

trait Admin {
  //TODO Ensure these method definitions are proper for the Admin Service
  def approveUser(user: User): Boolean
  def resetPassword(user: User): Boolean
  def deleteUser(user: User): Boolean

}