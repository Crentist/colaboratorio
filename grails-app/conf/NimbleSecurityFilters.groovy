/*
 *  Nimble, an extensive application base for Grails
 *  Copyright (C) 2010 Bradley Beddoes
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
import grails.plugin.nimble.core.AdminsService
import grails.plugin.nimble.security.NimbleFilterBase
import org.apache.shiro.SecurityUtils
import grails.plugin.nimble.core.UserBase
import org.apache.shiro.subject.Subject
import bfcrowd.Project

/**
 * Filter that works with Nimble security model to protect controllers, actions, views
 *
 * @author Bradley Beddoes
 */
class NimbleSecurityFilters extends NimbleFilterBase {

	def filters = {

		// Content requiring users to be authenticated
		secure(controller: "main") {
			before = { accessControl { true } }
		}
		
	/*	secureedit(controller: "user", action: "edit") { //Me sigue filtrando por rol admin
			before = { accessControl { true } }
		}*/

		// Account management requiring authentication
		accountsecure(controller: "account", action: "(changepassword|updatepassword|changedpassword)") {
			before = { accessControl { true } }
		}

		// This should be extended as the application adds more administrative functionality
		administration(controller: "(admins|user|group|role)") {
			before = {
				accessControl { role(AdminsService.ADMIN_ROLE) }
			}
		}
		
		/*administration(controller: "user", action:"*", actionExclude:"edit") {
			before = {
				accessControl { role(AdminsService.ADMIN_ROLE) }
			}
		}*/
		
		recommendation(controller: "recommendation", action:"*") {
			before = {
				accessControl {
					role("Investigador")
				}
			}
		}
		
		project(controller: "project", action:"*", actionExclude:"show") {
			before = {
				accessControl {
					role("Investigador")
				}
			}
		}
		
		colaborator(controller: "colaborator", action:"*") {
			before = {
				accessControl {
					true //O algún rol en particular
				}
			}
		}
		
		colaborator(controller: "colaborator", action:"project") {
			before = {
				accessControl { 
					UserBase.get(SecurityUtils.subject.principal).getMyProjects().contains(Project.get(params.id))
				}
			}
		}
		
		contribution(controller: "contribution", action:"*") {
			before = {
				accessControl { role(AdminsService.ADMIN_ROLE) }
			}
		}
		
		recommendationCsvImporter(controller: "recommendationCsvImporter", action:"*") {
			before = {
				accessControl {
					role("Investigador")
				}
			}
		}
		
	}
}
