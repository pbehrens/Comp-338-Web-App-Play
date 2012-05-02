package controllers
import models.services._
import models.objects._
import models.services.impl._
import models.services.impl.DefaultStaffServiceComponent


object Registry extends DefaultUserRepositoryComponent
                   with DefaultUserFactoryComponent
                   with DefaultReservationFactoryComponent
                   with DefaultResourceFactoryComponent
                   with DefaultMemberServiceComponent
                   with DefaultStaffServiceComponent
                   with DefaultGuestServiceComponent
                   with DefaultEmailServiceComponent
                   with DefaultAuthenticationServiceComponent
  {
  val userRepository:UserRepository = new DefaultUserRepository()
  val memberService:MemberService = new DefaultMemberService()
  val staffService:StaffService = new DefaultStaffService
  val guestService:GuestService = new DefaultGuestService()
  val emailService:EmailService = new DefaultEmailService()
  val authenticationService:AuthenticationService = new DefaultAuthenticationService()
  val matt = userFactory.create(userFactory.iDNumber, "mkemnetz@luc.edu", "password", "Matthew", "Kemnetz", new Role(false, true, true, true))
  userRepository.addUser(matt)
  val pat = userFactory.create(userFactory.iDNumber, "pbehrens@luc.edu", "password", "Matthew", "Kemnetz", new Role(false, true, true, true))
  userRepository.addUser(pat)
  userRepository.addResource(1, "Basketball", "Play basketball with it", matt)
  userRepository.addResource(2, "Baseball", "Play baseball with it", matt)
  userRepository.addResource(3, "Bananas", "Eat bananas", matt)
  
}