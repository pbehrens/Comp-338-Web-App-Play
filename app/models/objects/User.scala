package models
package objects

import play.db.jpa._

case class User(email: String, name: String, password: String)

trait User {
  //TODO is this the best way to do this? I made these all vars so the AdminService could modify Users...replace with new User instance
  def userID: Int
  def email: String
  def firstName: String
  def lastName: String
  def role: Role
  def ==(check: User): Boolean = {
    (this.userID == check.userID) && (this.email == check.email) && (this.firstName == check.firstName) && (this.lastName == check.lastName) &&
      (this.lastName == check.lastName) && (this.role == check.role)
  }
  
    /**
   * Authenticate a User.
   */
  def authenticate(email: String, password: String): Option[User]
  
  
}

object User{
  def connect(email: String, password: String) = {  }
}

object NullUser extends User{
	var userID = -1
	var email = ""
	var firstName = ""
	var lastName = ""
	var role = new Role(false) //not a guest. Everything else defaults.

}
