package importer

import colaboratorio.Task
import colaboratorio.Project

//import org.apache.shiro.subject.Subject
/**
 * RecommendationCsvImporterController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
class RecommendationCsvImporterController {

	def index() {
		flash.message = null
		flash.error = null
		flash.success = null
		[layout_nosecondarymenu: true, ownProjects: getAuthenticatedUser().ownedProjects]
    }
	
	def importFile(int projectId) {
		flash.message = null
		flash.error = null
		flash.success = null
		def project = Project.get(projectId)
		if (!getAuthenticatedUser().ownedProjects.contains(project)) {
			flash.error = 'Error project id'
			[layout_nosecondarymenu: true]
			return
		}
	    def f = request.getFile('filecsv')
	    if (f.empty) {
	        flash.message = 'file cannot be empty'
	        render(view: 'importFile')
	        return
	    }
		//validate file or do something crazy hehehe
		
		//now transfer file
		def webrootDir = servletContext.getRealPath("/") //app directory
		println webrootDir 
		File fileDest = new File(webrootDir,"last_import.csv")
		f.transferTo(fileDest)
		RecommendationCsvImporter importer = new RecommendationCsvImporter()
		def result = importer.importFileWithProject(project)
		if (result.errors.size() > 0)
	    	flash.message = 'Passed: '+result.passed
		else
			flash.success = 'Passed: '+result.passed
		flash.error = 'Errors: '+result.errors
	    //flash.message = 'Errors: '+result.errors
		
		[layout_nosecondarymenu: true]
	}
}
