package controllers
import models.services._
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
}