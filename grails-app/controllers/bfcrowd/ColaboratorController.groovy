package bfcrowd

//import grails.transaction.Transactional;

import java.text.SimpleDateFormat

import grails.converters.JSON
//import grails.plugins.rest.client.RestBuilder
import groovy.json.JsonSlurper

import org.apache.commons.collections.map.MultiValueMap
import org.springframework.util.LinkedMultiValueMap
//import org.apache.shiro.subject.Subject

import colaboratorio.Contribution;
import colaboratorio.Project;
import colaboratorio.Task;
import colaboratorio.User;

/**
 * ColaboratorController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
class ColaboratorController {
	
	def index() {
		if ( getAuthenticatedUser() ) {
			
			//Subject u = Subject.findByUsername("admin")
	        //render "holasxs " + getAuthenticatedUser().myProjects
			[myProjects: getAuthenticatedUser().myProjects, otherProjects: Project.getAll() - getAuthenticatedUser().myProjects, layout_nosecondarymenu: true]
			//println "current user : $authenticatedUser"
		} else
			render "No hay usuario logueado"
    }
	
	/**def joinProject(String name) {
		if(name){
			Project p = Project.findByName(name)
			if(p){
				def user = getAuthenticatedUser()
				user.myProjects.add(p)

				if (!(p.usersXP[user.id]))
					p.usersXP[user.id]=0 //Initialize xp value on that project
				assert p.usersXP.get(user.id) == 0
			}
		}
		render template: "myProjects", model: [myProjects: getAuthenticatedUser().myProjects]
	}
	
	def joinProjectById(int id) {
		println "HGETYE"
		Project p = Project.get(id)
		if(p){
			def user = getAuthenticatedUser()
			user.myProjects.add(p)
			//println p.name
			println "HGETYE"
			println "hey "+(p.usersXP[user.id] != null)
			if (!(p.usersXP[user.id]))
				p.usersXP[user.id]=0 //Initialize xp value on that project
			assert p.usersXP.get(user.id) == 0
		}
		render template: "myProjects", model: [myProjects: getAuthenticatedUser().myProjects, otherProjects: Project.getAll() - getAuthenticatedUser().myProjects]
	}**/
	
	def leaveProjectById(int id) {
		Project p = Project.get(id)
		if(p){
			getAuthenticatedUser().myProjects.remove(p)
		}
		render template: "myProjects", model: [myProjects: getAuthenticatedUser().myProjects, otherProjects: Project.getAll() - getAuthenticatedUser().myProjects]
	}
	
	def otherProjects() {
		if ( getAuthenticatedUser() ) {
			[otherProjects: Project.getAll() - getAuthenticatedUser().myProjects]
		}
	}
	
	
	def project(int id) {
		if(id){
			Project p = Project.get(id)
			//println params["friends"]
			if(p){
				def badges = this.getUserBadges(getAuthenticatedUser(),p)
				//println "badges: "+badges
				def r = p.getRecommendationFor(getAuthenticatedUser())
				render view: "${p.type}", model: [project: p, recommendation: r, layout_nosecondarymenu: true, b: badges]
			} else
				render "error"
		}
	}
	
	def skip(int recommendationId) {
		def r = Task.get(recommendationId)
		def u = getAuthenticatedUser()
		r.dateAssigned = new Date(0)
		u.skippedRecom.add(r)
		Project p = Project.get(r.project.id)
		def recom = p.getRecommendationFor(getAuthenticatedUser())
		//println("skippeds: "+u.skippedRecom)
		//println("skippeds size: "+u.skippedRecom.size())
		def badges = this.getUserBadges(getAuthenticatedUser(),p)
		
		render view: "${p.type}", model: [project: p, recommendation: recom, layout_nosecondarymenu: true, b:badges]
	}
	
	def saveContribution(int recommendationId, String state, String text) {
		// Acá deberían ser sólo Recommendations a las que el Subject pueda contribuir
		def r = Task.get(recommendationId)
		Project p = Project.get(r.project.id)
		//println "state: "+state
		//println "text: "+text
		if (state) {
			def u = getAuthenticatedUser()
			Contribution c = new Contribution([text: text, state: state, recomendation: r, user: u, solvedDate: new Date()])
			c.save(flush:true)
			//println c
			def obtainedXP = p.xpValue + this.checkBonus(p, u)
			//println "pre usersXP: "+p.getUserXPByID(u.id)
			//println "bonus: "+this.checkBonus(p, u)
			//println "obtainedXP: "+obtainedXP
			//println "pre experiencia usuario: "+p.experiencia
			//println "usersXP: "+p.usersXP
			p.usersXP[u.id] +=  obtainedXP
			u.myXP += obtainedXP
			//println "usersXP usuario: "+p.usersXP[u.id]
			//println "experiencia usuario: "+p.experiencia
			//println "myXP: "+u.myXP 
			//println (p as JSON)
			//p.save()
			
			//Solución parcial para permitir que siempre hayan recomendaciones para resolver!! begin
			//u.skippedRecom.add(r)
			//Solución parcial para permitir que siempre hayan recomendaciones para resolver!! end
			
			//println("skippeds: "+u.skippedRecom)
			//println("skippeds size: "+u.skippedRecom.size())
			this.checkUserBadges(u, p)
		}
		
		def badges = this.getUserBadges(getAuthenticatedUser(),p)
		
		def recom = p.getRecommendationFor(getAuthenticatedUser())
		render view: "${p.type}", model: [project: p, recommendation: recom, layout_nosecondarymenu: true, b:badges]
	}
	
	def checkBonus(Project p, User u) {
		
		def sdf = new SimpleDateFormat("dd/MM/yyyy")
		def today = new Date()
		
		//Acá tiene que ser las contrib de ese proyecto, no todas
		if (u.getMyContributions().count {it.getRecomendation().getProject() == p && sdf.format(it.solvedDate) == sdf.format(today) } == p.getRequiredForBonus()) {
		//if (u.getMyContributions().count { sdf.format(it.solvedDate) == sdf.format(today) } == p.getRequiredForBonus()) {
			
			assignBadge(u, p, "First Bonus") //Cuidado acá, y si ya la tiene???
			return p.bonusXP
			}
			else return 0
		
	}
	
	def checkUserBadges(User u, Project p) {
		/** 
		 * Chequea si se le debe otorgar o no una insignia a un usuario
		 */
		def email = getAuthenticatedUser().getProfile().getEmail()
		def app = p.getName().replace(" ", "_").replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u").toLowerCase()
				
		//RestBuilder rest = new RestBuilder()
		//Obtengo las badges del proyecto
		def resp = rest.get("http://cientopolis.lifia.info.unlp.edu.ar/badges-api/issuers/bfcrowd_${app}/badges")
		//println "http://163.10.5.42:9292/issuers/bfcrowd_${app}/badges"
		//println "hola"
		//println "badges json check: "+resp.json
		//println resp.json
		if (resp.json != [:]) {
			def badgesProject = resp.json
			
			//LinkedMultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>()
			form.add("email", email)
							
			if (u.getMyContributions().count {it.getRecomendation().getProject() == p} == 1) {
				//Doy la insignia de primer tarea resuelta
				//Busco la insignia
				String badge = "null"
				int iterator = 0
				while (badgesProject[iterator]["name"] != "First contribution") {
					iterator++
					//println iterator
				}
				
				String idBadge = badgesProject[iterator]["id_badge_class"]
				//println "idBadge: "+idBadge
				//println "http://163.10.5.42:9292/issuers/bfcrowd_${app}/badges/${idBadge}/instances"
				resp = rest.post("http://cientopolis.lifia.info.unlp.edu.ar/badges-api/issuers/bfcrowd_${app}/badges/${idBadge}/instances") {
					accept("text/html")
					contentType("application/x-www-form-urlencoded")
					body(form)
				}
				//println "dio badge o no: "+resp.json
			}
		}
	}
	
	def assignBadge(User u, Project p, String badgeName) {
		
		def email = u.getProfile().getEmail()
		def app = p.getName().replace(" ", "_").replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u").toLowerCase()
		
		//RestBuilder rest = new RestBuilder()
		//Obtengo las badges del proyecto
		def resp = rest.get("http://cientopolis.lifia.info.unlp.edu.ar/badges-api/issuers/bfcrowd_${app}/badges")
		def badgesProject = resp.json
		//println "badges json assign: "+resp.json
		//LinkedMultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>()
		form.add("email", email)
		//println "badges project en 0: "+badgesProject[0]
		//println "badges project en 1: "+badgesProject[1]
		String badge = "null"
		int iterator = 0
		while (badgesProject[iterator]["name"] != "First bonus") {
			iterator++
		}
		String idBadge = badgesProject[iterator]["id_badge_class"]
		resp = rest.post("http://cientopolis.lifia.info.unlp.edu.ar/badges-api/issuers/bfcrowd_${app}/badges/${idBadge}/instances") {
			accept("text/html")
			contentType("application/x-www-form-urlencoded")
			body(form)
		}
		//println "dio badge o no: "+resp.json
	}
	
	def getUserBadges(User u, Project p) {
		//RestBuilder rest = new RestBuilder()
		//def resp = rest.get("http://ciencia.lifia.info.unlp.edu.ar/badges-api/issuers/colaboratorio/instances/${u.email}")
		
		//RestBuilder rest = new RestBuilder()
		def email = getAuthenticatedUser().getProfile().getEmail()
		def app = p.getName().replace(" ", "_").replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u").toLowerCase()
		//def URL = "http://ciencia.lifia.info.unlp.edu.ar/badges-api/issuers/bfcrowd_${app}/instances/${email}"
		def resp = rest.get("http://cientopolis.lifia.info.unlp.edu.ar/badges-api/issuers/bfcrowd_${app}/instances/${email}")
		//def slurper = new JsonSlurper()
		//def result = slurper.parse(resp.json.toString())
		//println result.name
		//println URL
		//println "resp badges usuario: "+resp.json[email]
		//def alala = resp.json[email].toArray()
		//println alala.size()
		if (resp.json[email] == null)
			return []
		return resp.json[email]
		//println resp.json["cacho@cacho.com"][0]["name"]
		
		
		/**def resp2 = rest.post("https://ciencia.lifia.info.unlp.edu.ar/badges/90812gjd/instances") {
			contentType "application/json"
			json {
				email = "cacho@cacho.com"
			}
		}**/

	}
	
}
