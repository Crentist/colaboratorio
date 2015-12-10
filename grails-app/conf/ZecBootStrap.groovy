/**import grails.plugin.nimble.InstanceGenerator
import grails.plugin.nimble.core.AdminsService
import grails.plugin.nimble.core.Role
import grails.plugin.nimble.core.RoleService
import grails.plugin.nimble.core.UserBase**/
import com.colaboratorio.Role
import com.colaboratorio.UserRole
import colaboratorio.Project
import colaboratorio.ProjectDefinition
import colaboratorio.Task
import colaboratorio.User
//import grails.plugins.rest.client.RestBuilder

class ZecBootStrap {
	
	def grailsApplication
	def userService
	def roleService

    def init = { servletContext ->

		def adminRole = new Role('ROLE_ADMIN').save()
		def userRole = new Role('ROLE_USER').save()

		def testUser = new User('testUser', 'testUser').save()

		UserRole.create testUser, adminRole, true

		assert User.count() == 1
		assert Role.count() == 2
		assert UserRole.count() == 1
		println "dale gato"
		if(!Project.findByName("Explorando La Plata")) {
			/**Project project1 = new Project(name: "Wikipedia tasks", description: "Help us improve the contents on Wikipedia!", xpValue: 25, bonusXP: 25, requiredForBonus: 2, type:"taskProject")
			project1.addOwner(researcher)
			project1.save()
			Project project2 = new Project(name: "Catálogo de aves autóctonas", description: "Ayudanos a determinar si el ave de la foto corresponde a un ejemplar propio de la región patagónica. PROYECTO PARA ENSEÑAR A USAR COLABORATORIO", xpValue: 15, bonusXP: 15, requiredForBonus: 3, type:"imageProject")
			project2.addOwner(researcher)
			project2.save()
			Project project3 = new Project(name: "Encuesta de polución sonora", description: "Responda a esta encuesta para ayudarnos a entender la polución sonora de nuestra ciudad.", xpValue: 10, bonusXP: 10, requiredForBonus: 3, type:"taskProject")
			project3.addOwner(researcher)
			project3.save()**/
			Project project4 = new Project(name: "Explorando La Plata", 
										   description: "Este proyecto busca que los voluntarios identifiquen edificios históricos o de importancia en la ciudad de La Plata. Las fotos aquí presentes fueron obtenidas a partir de la búsqueda de las palabras 'La Plata' en Flickr bajo licencia Creative Commons. Se reservan todos los derechos a sus respectivos autores.", 
										   type:"imageProject")
			project4.addOwner(researcher)
			def projectDefinition = new ProjectDefinition(xpValue: 10,
															bonusXP: 25,
															requiredForBonus: 8,
															repeatableBetweenUsers: true,
															repeatableBySingleUser: false,
															maxRepeats: -1,
															project: project4)
			if (!projectDefinition.validate()) {
				projectDefinition.errors.each {
					println it
				}
			}
			project4.definition = projectDefinition
			if (!project4.validate()) {
				project4.errors.each {
					println it
				}
			}
			/**def projectDefinition = new ProjectDefinition(xpValue: 10, 
														  bonusXP: 25, 
														  requiredForBonus: 8, 
														  repeatableBetweenUsers: true, 
														  repeatableBySingleUser: false, 
														  maxRepeats: -1, 
														  project: project4)
			projectDefinition.save()**/
			//project4.setDefinition(projectDefinition)
			/**project4.definition = projectDefinition
			project4.addOwner(researcher)
			if (project4.validate())
				project4.save(flush: true)**/
			
			for (int i = 1; i<=50;i++) {
				def task = new Task()
				task.addInstructionsForm("¿Podés reconocer en esta foto algún edificio representativo de La Plata? (Por ejemplo, la municipalidad, el Teatro Argentino, la catedral, etc). Tené en cuenta que no hace falta que estés 100% seguro, ya que muchas personas van a analizar esta misma imagen.",
										["Sí", "No"], //respuestas posibles
										false, //es multiple choice?
										"ColaboratorioLaPlata/laplata"+i+".jpg" //imagen
										)
				//project4.addTask(task)
				//task.setProject(project4)// = project4
				/**task.project = project4
				if (task.validate())
					task.save(flush: true)**/
				/**if (!task.save()) {
					task.errors.each {
						println it
					}
				}**/
				//task.save()
				//project4.save()
			}
			
			
			
			/**Project project4 = new Project(name: "Drink supplier", description: "First aid on all things alcohol!", xpValue: 5, bonusXP: 5, requiredForBonus: 3)
			project4.addOwner(researcher)
			project4.save()**/
			/**def recomm1 = new Recommendation(property: "peopleFrom",
											path: "path",
											fromPage: "Rosario",
											toPage: "Lionel_Messi",
											solved: false,
											date: new Date(),
											instructions: "Agregar el articulo Lionel Messi (http://en.wikipedia.org/wiki/Lionel_Messi) a la categoría People From Rosario.",
											checkboxMode: "Checkbox")
			recomm1.project = project1
			recomm1.save()
			
			def recomm2 = new Recommendation(property: "peopleFrom",
											path: "path2",
											fromPage: "Paris",
											toPage: "Pierre_Curie",
											solved: false,
											date: new Date(),
											instructions: "Agregar el articulo Pierre Curie (http://en.wikipedia.org/wiki/Pierre_Curie) a la categoría People From Paris.",
											checkboxMode: "Checkbox")
			
			recomm2.project = project1
			recomm2.save()
			
			def recomm3 = new Recommendation(property: "peopleFrom",
											path: "path3",
											fromPage: "fromPage",
											toPage: "toPage",
											solved: false,
											date: new Date(),
											instructions: "Agregar el articulo Robin Moore (http://en.wikipedia.org/wiki/Robin_Moore) a la categoría People From Boston, Massachusetts.",
											checkboxMode: "Checkbox")
	
			recomm3.project = project1
			recomm3.save()
			
			def recomm4 = new Recommendation(property: "null",
											path: "null",
											fromPage: "null",
											toPage: "null",
											solved: false,
											date: new Date(),
											instructions: "¿Pertenece este ave a la región patagónica?",
											checkboxMode: "Checkbox",
											repeatableBetweenUsers: true,
											repeatableBySingleUser: true,
											maxRepeats: -1,
											imagePath: "blueparrot.jpg")
			
			recomm4.project = project2
			recomm4.save()
			
			def recomm5 = new Recommendation(property: "null",
											path: "null",
											fromPage: "null",
											toPage: "null",
											solved: false,
											date: new Date(),
											instructions: "¿Pertenece este ave a la región patagónica?",
											checkboxMode: "Checkbox",
											repeatableBetweenUsers: true,
											repeatableBySingleUser: true,
											maxRepeats: -1,
											imagePath: "cometocino.jpeg")

			recomm5.project = project2
			recomm5.save()
						
			def recomm6 = new Recommendation(property: "null",
											path: "null",
											fromPage: "null",
											toPage: "null",
											solved: false,
											date: new Date(),
											instructions: "¿Pertenece este ave a la región patagónica?",
											checkboxMode: "Checkbox",
											repeatableBetweenUsers: true,
											repeatableBySingleUser: true,
											maxRepeats: -1,
											imagePath: "loicacomun.jpg")
			
			recomm6.project = project2
			recomm6.save()
			
			def recomm7 = new Recommendation(property: "null",
											path: "null",
											fromPage: "null",
											toPage: "null",
											solved: false,
											date: new Date(),
											instructions: "¿Pertenece este ave a la región patagónica?",
											checkboxMode: "Checkbox",
											repeatableBetweenUsers: true,
											repeatableBySingleUser: true,
											maxRepeats: -1,
											imagePath: "patagon.jpg")

			recomm7.project = project2
			recomm7.save()
			
			def recomm8 = new Recommendation(property: "null",
											path: "null",
											fromPage: "null",
											toPage: "null",
											solved: false,
											date: new Date(),
											instructions: "¿Pertenece este ave a la región patagónica?",
											checkboxMode: "Checkbox",
											repeatableBetweenUsers: true,
											repeatableBySingleUser: true,
											maxRepeats: -1,
											imagePath: "puffinnorway.jpg")

			recomm8.project = project2
			recomm8.save()**/
		}
		
		//Crear las badges por aquí
		
		//def proyectos = Project.getAll()*.name
		//RestBuilder rest = new RestBuilder()
		//for (int i = 0; i<proyectos.size();i++) {
			//si alguna vez anda con tildes, sacar los 5 últimos replace
			//def app = proyectos[i].replace(" ", "_").replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u").toLowerCase()
			//def resp = rest.get("http://163.10.5.42/badges-api/issuers/asdasd/badges")
			//def resp = rest.get("http://163.10.5.42:9292/issuers/bfcrowd_${app}/badges")
			//println resp.json
			//println (resp.json == [:])
			
			//id_app: 'bfcrowd_'+app,
			//name: 'BFCrowd '+proyectos[i],
			/**
			if (resp.json == []) {
				def contenido =
				[
				   [
					   id_app: 'bfcrowd_',
					   name: 'BFCrowd ',
					   url: 'http://ciencia.lifia.info.unlp.edu.ar/bfcrowd',
					   badges : [
						[ name: "First contribution", imageUrl: "http://163.10.5.42/images/bfcrowdimages/firstContribution.png", criteriaUrl: "http://ciencia.lifia.info.unlp.edu.ar/bfcrowd/badges", description: "My first contribution"],
						[ name: "First bonus", imageUrl: "http://163.10.5.42/images/bfcrowdimages/firstBonus.jpg", criteriaUrl: "http://ciencia.lifia.info.unlp.edu.ar/bfcrowd/badges", description: "First bonus"]
						]
					 
				   ]
				]
				
				println contenido
				resp = rest.post("http://163.10.5.42:9292/carga-json") {
					contentType "application/json"
							body {
								contenido
							}
					   }
			}
		}**/
		  /** ,
		   [
			   id_app: 'bfcrowd_Catálogo_de_aves_autóctonas',
			   name: 'BFCrowd Catálogo de aves autóctonas',
			   url: 'http://ciencia.lifia.info.unlp.edu.ar/bfcrowd',
			   badges : [
				[ name: "First contribution", imageUrl: "http://163.10.5.42/images/bfcrowdimages/firstContribution.png", criteriaUrl: "http://ciencia.lifia.info.unlp.edu.ar/bfcrowd/badges", description: "My first contribution"],
				[ name: "First bonus", imageUrl: "http://163.10.5.42/images/bfcrowdimages/firstBonus.jpg", criteriaUrl: "http://ciencia.lifia.info.unlp.edu.ar/bfcrowd/badges", description: "First bonus"]
				]
		   ]
		   
	   ]**/
		/**
		def proyectos = Project.getAll()*.name
		RestBuilder rest = new RestBuilder()
		//println proyectos.size()
		for (int i = 0; i<proyectos.size();i++) {
			//println "hola"
			//si alguna vez anda con tildes, sacar los 5 últimos replace
			def app = proyectos[i].replace(" ", "_").replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u").toLowerCase()
			//def resp = rest.get("http://163.10.5.42/badges-api/issuers/asdasd/badges")
			def resp = rest.get("http://cientopolis.lifia.info.unlp.edu.ar/badges-api/issuers/bfcrowd_${app}/badges")
			//println "tiene o no: "+resp.json
			//println (resp.json == [:])
			
			if (resp.json == []) {
				//println "no tiene"
				def contenido =
				[
				   [
					   id_app: 'bfcrowd_'+app,
					   name: 'BFCrowd '+app,
					   url: 'http://cientopolis.lifia.info.unlp.edu.ar/badges-api/Colaboratorio',
					   badges : [
						[ name: "First contribution", imageUrl: "http://163.10.5.42/images/bfcrowdimages/firstContribution.png", criteriaUrl: "http://ciencia.lifia.info.unlp.edu.ar/bfcrowd/badges", description: "My first contribution"],
						[ name: "First bonus", imageUrl: "http://163.10.5.42/images/bfcrowdimages/firstBonus.jpg", criteriaUrl: "http://ciencia.lifia.info.unlp.edu.ar/bfcrowd/badges", description: "First bonus"]
						]
					 
				   ]
				]

				//println contenido

				resp = rest.post("http://cientopolis.lifia.info.unlp.edu.ar/badges-api/carga-json") {
					contentType "application/json"
						json {
							contenido
							}
						}

				//println "resp despues de crear: "+resp.json
			}
		}**/
		
		
		
		/**resp = rest.post("http://163.10.5.42/badges-api/carga-json") {
		contentType "application/json"
				body {
					contenido
				}	
		   }**/
		
		
		/**def contenido = 
		[
			[
			id_app:  'bfcrowd_Wikipedia_tasks',
			name: 'BFCrowd Wikipedia Tasks',
			url:  'http://ciencia.lifia.info.unlp.edu.ar/bfcrowd',
			badges: (
					[
	            	name: "First contribution",
	            	imageUrl: "http://dab1nmslvvntp.cloudfront.net/wp-content/uploads/2014/11/1415490092badge.png",
	            	criteriaUrl: "http://ciencia.lifia.info.unlp.edu.ar/bfcrowd/badges",
	            	description: "My first contribution"
					] 
					)
				
			]
		]
				
		RestBuilder rest = new RestBuilder()
		
		//def resp = rest.get("http://ciencia.lifia.info.unlp.edu.ar/badges-api/")
		//println resp.json
		
		def resp = rest.post("http://ciencia.lifia.info.unlp.edu.ar/badges-api/carga-json") {
		 contentType "application/json"
			 json {
				 contenido
				 }
			 }
		 
		 println resp.json**/
    }
    def destroy = {
    }
}
