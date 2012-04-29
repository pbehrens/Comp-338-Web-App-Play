package models
package services.impl

import models.services._

trait DefaultUserServiceComponent extends UserServiceComponent {
  this: UserRepositoryComponent =>


  class DefaultUserService extends UserService {
//user services go here
  }
}