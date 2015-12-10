package colaboratorio

//import grails.plugins.rest.client.RestBuilder
import com.colaboratorio.Subject

class User extends Subject {

	// Extend UserBase with your custom values here
	String facebookID
	boolean requests //Indicates whether the user wants to be promoted to researcher

	/* Default (injected) attributes of GORM */
//	Long	id
//	Long	version
	
	/* Automatic timestamping of GORM */
//	Date	dateCreated
//	Date	lastUpdated
	
//	static	belongsTo	= []	// tells GORM to cascade commands: e.g., delete this object if the "parent" is deleted.
//	static	hasOne		= [profile:Profile]	// tells GORM to associate another domain object as an owner in a 1-1 mapping
	static	hasMany		= [myContributions:Contribution, myProjects:Project, skippedRecom:Task, ownedProjects: Project]	// tells GORM to associate other domain objects for a 1-n or n-m mapping
//	static	mappedBy	= []	// specifies which property should be used in a mapping
	
	static	mapping = {
		
	}
	
	static	constraints = {
		facebookID nullable:true, blank: true
	}
	
	public String toString() {
		//return username;
	}
	
	def next() {
		//Siguiente recomendación o contribución para completar
	}
	
	def hasRole(String role) {
		def roles = this.getRoles() //Nimble method
		for (r in roles) {
			if (r.name == role)
				return true
		}
		return false		
	}
	
	def rolesByName() {
		def roles = this.getRoles() //Nimble method
		def rolesNames = []
		for (r in roles) {
			rolesNames.add(r.name)
		}
		return rolesNames
	}
	
	def addProjectAsOwned(Project project) {
		if (!ownedProjects){
			this.ownedProjects = new ArrayList()
		}
		this.ownedProjects.add(project)
	}
	
	def getMyProjectContributions() {
		
		//this.myContributions.each { }

	}
	
	def joinProject(Project project) {
		if (!this.myProjects)
			this.myProjects = new HashSet()
		this.myProjects.add(project)
		project.addCollaborator(this)
	}
	
	def skipTask(Task task) {
		this.skippedRecom.add(task)
	}
	

	//def checkBadges(Subject u) {
		
	/**
	 * if (tiene una recomm)
	 * 		dar badge de primer recomendacion (en el proyecto)
	 * O
	 * Project.grantBadge() (si es que tiene lo requerido)
	 *
	 */
		
	//}

	/**def getEsos (Long id, String projectName) {
		
		RestBuilder rest = new RestBuilder()
		def email = getAuthenticatedUser().getProfile().getEmail()
		def app = projectName.replace(" ", "_")
		def resp = rest.get("https://ciencia.lifia.info.unlp.edu.ar/badges/issuers/bfcrowd_${app}/instances/${email}")
		return resp.json**/
		
		/**def resp = rest.post("https://cientificos-badges-api.herokuapp.com/badges/90812gjd/instances") {
			contentType "application/json"
			json {
				email = "cacho@cacho.com"
			}
		}
		
		println resp.json**/
		
	//}

		
}