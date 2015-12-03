package bfcrowd


import static org.springframework.http.HttpStatus.*
import colaboratorio.Project;
import grails.transaction.Transactional
//import grails.plugins.rest.client.RestBuilder

/**
 * ProjectController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Transactional(readOnly = true)
class ProjectController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	private static final okcontents = ['image/png', 'image/jpeg', 'image/gif']
	
	def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Project.list(params), model:[projectInstanceCount: Project.count(), layout_nosecondarymenu: true]
    }

	def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Project.list(params), model:[projectInstanceCount: Project.count(), layout_nosecondarymenu: true]
    }

    def show(Project projectInstance) {
		/*
		 * Modificar para tener una vista personalizada del proyecto 
		 */
        respond projectInstance, [model:[layout_nosecondarymenu: true]]
    }

    def create() {
        respond new Project(params), [model:[layout_nosecondarymenu: true]]
    }

    @Transactional
    def save(Project projectInstance) {
        if (projectInstance == null) {
            notFound()
            return
        }

		def f = request.getFile('logo')
		// List of OK mime-types
		if (!okcontents.contains(f.getContentType())) {
			flash.message = "Logo must be one of: ${okcontents}"
			respond projectInstance.errors, view:'create'
			return
		}
		
		projectInstance.logo = f.bytes
		projectInstance.logoType = f.contentType
		
		// Save the image and mime type
		log.info("File uploaded: $projectInstance.logoType")
		
        if (projectInstance.hasErrors()) {
            respond projectInstance.errors, view:'create'
            return
        }
		
		flash.message = "Logo (${projectInstance.logoType}, ${projectInstance.logo.size()} bytes) uploaded."
		
        projectInstance.save flush:true
		println "asdf"
		render view: "show", model: [projectInstance:projectInstance]
		/*
        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'projectInstance.label', default: 'Project'), projectInstance.id])
                redirect projectInstance
            }
            '*' { respond projectInstance, [status: CREATED] }
        }
        */
    }
	
	def logo_image() {
		def logoProject = Project.get(params.id)
		if (!logoProject || !logoProject.logo || !logoProject.logoType) {
			response.sendError(404)
			return
		}
		response.contentType = logoProject.logoType
		response.contentLength = logoProject.logo.size()
		OutputStream out = response.outputStream
		out.write(logoProject.logo)
		out.close()
	}
	
    def edit(Project projectInstance) {
        respond projectInstance, [model:[layout_nosecondarymenu: true]]
    }

    @Transactional
    def update(Project projectInstance) {
        if (projectInstance == null) {
            notFound()
            return
        }

        if (projectInstance.hasErrors()) {
            respond projectInstance.errors, view:'edit'
            return
        }

        projectInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Project.label', default: 'Project'), projectInstance.id])
                redirect projectInstance
            }
            '*'{ respond projectInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Project projectInstance) {

        if (projectInstance == null) {
            notFound()
            return
        }

        projectInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Project.label', default: 'Project'), projectInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'projectInstance.label', default: 'Project'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}


