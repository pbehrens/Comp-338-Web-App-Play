package controllers
import models.services._
import models.services.impl._


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
  override val userRepository = new DefaultUserRepository()
  override val memberService = new DefaultMemberService()
  override val staffService = new DefaultStaffService()
  override val guestService = new DefaultGuestService()
  override val emailService = new DefaultEmailService()
  val authenticaionService = new DefaultAuthenticationService()
}