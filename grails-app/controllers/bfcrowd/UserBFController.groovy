package bfcrowd

/**import grails.plugin.nimble.InstanceGenerator
import grails.plugin.nimble.core.AdminsService
import grails.plugin.nimble.core.RoleService
import grails.plugin.nimble.core.Role
import grails.plugin.nimble.core.UserBase
import grails.plugin.nimble.core.AuthController
import grails.plugin.nimble.core.AccountController**/

/**import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.DisabledAccountException
import org.apache.shiro.authc.IncorrectCredentialsException
import org.apache.shiro.authc.UsernamePasswordToken**/

import colaboratorio.User;
/**
 * UserBFController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
class UserBFController {

	//static scaffold = true
//	def index = { }
	//def grailsApplication
	/**def nimbleService
	def userService
	def adminsService
	def roleService**/
	
	
	def requestPromotion(Long id) {
		User user = User.get(id)
		user.setRequests(true)
		//redirect(uri: '/')
		render(view: 'requestedpromotion')
	}
	
	def signinfb() {
		//params["pass"] = "lapass"
		//println UserBase.findByUsername("researcher")
		/**if(!UserBase.findByUsername(params["username"])) {
			def user = InstanceGenerator.user(grailsApplication)
			user.username = params["username"]
			user.pass = "a"+params["username"]
			user.passConfirm = "a"+params["username"]
			user.facebookID = params["username"]
			user.enabled = true

			def userProfile = InstanceGenerator.profile(grailsApplication)
			userProfile.fullName = params["fullName"]
			userProfile.owner = user
			userProfile.email = params["email"]
			user.profile = userProfile

			log.info("Creating default user account with username:user")

			def savedUser = userService.createUser(user)
			if (savedUser.hasErrors()) {
				savedUser.errors.each { log.error(it) }
				throw new RuntimeException("Error creating example user")
			}
			
			def defaultRole = Role.findByName("Científico Ciudadano")
			roleService.addMember(user, defaultRole)
			
			//redirect controller: 'account', action: 'createduser'
		}**/
		//else {
			//login
			//redirect(action:AuthController.signin(params["username"], params["username"], false))
			//def authToken = new UsernamePasswordToken(params["username"], "a"+params["username"])
					
			log.info("Attempting to authenticate user, $params[\"username\"]. RememberMe is $authToken.rememberMe")

		/**try {
				SecurityUtils.subject.login(authToken)
				userService.createLoginRecord(request)
	
				def targetUri = "/"
				session.removeAttribute(AuthController.TARGET)
	
				log.info("Authenticated user, $params[\"username\"]")
				if (userService.events["login"]) {
					log.info("Executing login callback")
					def newUri = userService.events["login"](authenticatedUser, targetUri, request)
					if (newUri != null) {
						targetUri = newUri
					}
				}
				log.info("Directing to content $targetUri")
				redirect(uri: targetUri)
				return
			}
			catch (IncorrectCredentialsException e) {
				log.info "Credentials failure for user '$params[\"username\"]'."
				log.debug(e)
	
				flash.type = 'error'
				flash.message = message(code: "nimble.login.failed.credentials")
			}
			catch (DisabledAccountException e) {
				log.info "Attempt to login to disabled account for user '${params["username"]}'."
				log.debug(e)
	
				flash.type = 'error'
				flash.message = message(code: "nimble.login.failed.disabled")
			}
			catch (AuthenticationException e) {
				log.info "General authentication failure for user '${params["username"]}'."
				log.debug(e)
	
				flash.type = 'error'
				flash.message = message(code: "nimble.login.failed.general")
			}**/
			redirect(uri: '/')
		//}
		//redirect(action: AccountController.saveuser(), params: this.params)
		//AccountController.saveuser()
		
	}
}
