package bfcrowd

import groovy.json.JsonBuilder
import grails.converters.JSON
//import grails.plugins.rest.client.RestBuilder
import groovy.json.JsonOutput

import org.apache.commons.collections.map.MultiValueMap
import org.springframework.util.LinkedMultiValueMap

import colaboratorio.Project;
/**
 * HomeController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
class HomeController {

	//static scaffold = true
	def index() {
		
		/**def proyectos = Project.getAll()*.name
		RestBuilder rest = new RestBuilder()
		//println proyectos.size()
		for (int i = 0; i<proyectos.size();i++) {
			//println "hola"
			//si alguna vez anda con tildes, sacar los 5 últimos replace
			def app = proyectos[i].replace(" ", "_").replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u").toLowerCase()
			//def resp = rest.get("http://163.10.5.42/badges-api/issuers/asdasd/badges")
			def resp = rest.get("http://163.10.5.42:9292/issuers/bfcrowd_${app}/badges")
			println "tiene o no: "+resp.json
			//println (resp.json == [:])
			
			if (resp.json == []) {
				//println "no tiene"
				def contenido =
				[
				   [
					   id_app: 'bfcrowd_'+app,
					   name: 'BFCrowd '+app,
					   url: 'http://ciencia.lifia.info.unlp.edu.ar/colaboratorio',
					   badges : [
						[ name: "First contribution", imageUrl: "http://163.10.5.42/images/bfcrowdimages/firstContribution.png", criteriaUrl: "http://ciencia.lifia.info.unlp.edu.ar/colaboratorio/badges", description: "My first contribution"],
						[ name: "First bonus", imageUrl: "http://163.10.5.42/images/bfcrowdimages/firstBonus.jpg", criteriaUrl: "http://ciencia.lifia.info.unlp.edu.ar/colaboratorio/badges", description: "First bonus"]
						]
					 
				   ]
				]
				
				println contenido
				
				resp = rest.post("http://163.10.5.42:9292/carga-json") {
					contentType "application/json"
						json {
							contenido
							}
						}
				
				println "resp despues de crear: "+resp.json
			}
		}**/

		if (getAuthenticatedUser()?.hasRole("Científico Ciudadano")) {
			render view: "collabIndex"
		}
		else
			if (getAuthenticatedUser()?.hasRole("Investigador")) {
				render view: "researcherIndex"
			}
			else 
				if (getAuthenticatedUser()?.hasRole("SYSTEM ADMINISTRATOR"))
					render view: "adminIndex"
				else				
					render view: "index"
	}
	
	def joinProjectById(int id) {
		Project p = Project.get(id)
		if(p){
			def user = getAuthenticatedUser()
			user.myProjects.add(p)
			//if (!(p.usersXP[user.id]))
			p.setUserXP(user.id)
			//assert p.usersXP.get((user.id)) == 0
		}
		render template: "myProjects"
	}

	def leaveProjectById(int id) {
		Project p = Project.get(id)
		if(p){
			getAuthenticatedUser().myProjects.remove(p)
		}
		render template: "myProjects"
	}
	
	def badges() {
		render view: "badges"
	}
	
}
