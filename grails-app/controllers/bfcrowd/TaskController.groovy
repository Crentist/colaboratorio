package bfcrowd


import static org.springframework.http.HttpStatus.*
import colaboratorio.Task;
import grails.transaction.Transactional

/**
 * RecommendationController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Transactional(readOnly = true)
class TaskController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Recommendation.list(params), model:[recommendationInstanceCount: Recommendation.count()]
    }

	def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Recommendation.list(params), model:[recommendationInstanceCount: Recommendation.count()]
    }

    def show(Task recommendationInstance) {
        respond recommendationInstance
    }

    def create() {
        respond new Task(params)
    }

    @Transactional
    def save(Task recommendationInstance) {
        if (recommendationInstance == null) {
            notFound()
            return
        }

        if (recommendationInstance.hasErrors()) {
            respond recommendationInstance.errors, view:'create'
            return
        }

        recommendationInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'recommendationInstance.label', default: 'Recommendation'), recommendationInstance.id])
                redirect recommendationInstance
            }
            '*' { respond recommendationInstance, [status: CREATED] }
        }
    }

    def edit(Task recommendationInstance) {
        respond recommendationInstance
    }

    @Transactional
    def update(Task recommendationInstance) {
        if (recommendationInstance == null) {
            notFound()
            return
        }

        if (recommendationInstance.hasErrors()) {
            respond recommendationInstance.errors, view:'edit'
            return
        }

        recommendationInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Recommendation.label', default: 'Recommendation'), recommendationInstance.id])
                redirect recommendationInstance
            }
            '*'{ respond recommendationInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Task recommendationInstance) {

        if (recommendationInstance == null) {
            notFound()
            return
        }

        recommendationInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Recommendation.label', default: 'Recommendation'), recommendationInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'recommendationInstance.label', default: 'Recommendation'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
