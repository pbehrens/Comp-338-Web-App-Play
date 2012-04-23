package domain
package services

import domain.objects._

trait AdminServiceComponent {
  
  def adminService: AdminService
  
  trait AdminService {
    //TODO Ensure these method definitions are proper for the Admin Service
    def approveUser(user: User): Boolean
    def modifyUser(oldUser: User, newUser: User): Unit
    def deleteUser(user: User): Boolean
  }
  
}
