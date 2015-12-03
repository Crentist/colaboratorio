package bfcrowd


import static org.springframework.http.HttpStatus.*
import colaboratorio.Contribution;
import grails.transaction.Transactional

/**
 * ContributionController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Transactional(readOnly = true)
class ContributionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Contribution.list(params), model:[contributionInstanceCount: Contribution.count()]
    }

	def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Contribution.list(params), model:[contributionInstanceCount: Contribution.count()]
    }

    def show(Contribution contributionInstance) {
        respond contributionInstance
    }

    def create() {
        respond new Contribution(params)
    }

    @Transactional
    def save(Contribution contributionInstance) {
        if (contributionInstance == null) {
            notFound()
            return
        }

        if (contributionInstance.hasErrors()) {
            respond contributionInstance.errors, view:'create'
            return
        }

        contributionInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'contributionInstance.label', default: 'Contribution'), contributionInstance.id])
                redirect contributionInstance
            }
            '*' { respond contributionInstance, [status: CREATED] }
        }
    }

    def edit(Contribution contributionInstance) {
        respond contributionInstance
    }

    @Transactional
    def update(Contribution contributionInstance) {
        if (contributionInstance == null) {
            notFound()
            return
        }

        if (contributionInstance.hasErrors()) {
            respond contributionInstance.errors, view:'edit'
            return
        }

        contributionInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Contribution.label', default: 'Contribution'), contributionInstance.id])
                redirect contributionInstance
            }
            '*'{ respond contributionInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Contribution contributionInstance) {

        if (contributionInstance == null) {
            notFound()
            return
        }

        contributionInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Contribution.label', default: 'Contribution'), contributionInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'contributionInstance.label', default: 'Contribution'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
