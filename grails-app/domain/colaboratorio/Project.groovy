package colaboratorio

//import grails.plugins.rest.client.RestBuilder
/**
 * Project
 * A domain class describes the data object and its mapping to the database
 */
class Project {

	/* Default (injected) attributes of GORM */
//	Long	id
//	Long	version
	String name
	String description
	String type
	//Image logo
	//Tutorial tutorial
	
	/* Automatic timestamping of GORM */
//	Date	dateCreated
//	Date	lastUpdated
	static	belongsTo	= User
	static  mappedBy = [users: 'myProjects',	owners: 'ownedProjects'] // specifies which property should be used in a mapping 
	static	hasMany		= [tasks:Task, users:User, owners:User] //Debe conocer las contribuciones
//	static	belongsTo	= []	// tells GORM to cascade commands: e.g., delete this object if the "parent" is deleted.
	static	hasOne		= [usersXP:LinkedHashMap, logo:Image, tutorial:Tutorial, definition:ProjectDefinition] //usersXP: Colección que asocia a los usuarios con su experiencia ganada (ID de usuario + xp)
 	 												// tells GORM to associate another domain object as an owner in a 1-1 mapping
	
    static	mapping = {
		usersXP column: 'usersXP', sqlType: 'BLOB(100000)'
		description column: 'description', sqlType: 'varchar(1000)'
    }
    
	static	constraints = {
		name unique: true
		users nullable: true, blank: true
		owners nullable: false
		tasks nullable: true, blank: true
		usersXP nullable: true, blank: true
		logo nullable: true, blank: true
		tutorial nullable:true, blank: true
		type inList: ["imageProject", "taskProject"]
    }
	
	/*
	 * Methods of the Domain Class
	 */
	public String toString() {
		return "${name}";
	}
	
	public addTask(Task task) {
		if (!this.tasks)
			this.tasks = new HashSet()
		this.tasks.add(task)
		//task.setProject(this)
	}
	
	def findNextTaskForUser(User u) {
		return this.tasks.find{ Task w ->
			this.canTaskBeAssignedTo(u,w) && !u.skippedRecom.contains(w)
			}
	}
	
	def canTaskBeAssignedTo(User u,Task w){
		
		if ((w.contributions.size() == this.getDefinition().getMaxTaskRepeats()) && (this.getDefinition().getMaxTaskRepeats() != -1)){
			// maximas repeticiones
			return false
		}
		if (this.getDefinition().getAreTasksRepeatableBetweenUsers()) {
			// es repetible
			def user = w.contributions.find{ Contribution c ->
					c.user == u
					}
			if (!(this.getAreTasksRepeatableBySingleUser()))
				if (user) {
					// pero contribuyó a la recomendacion
					return false
				}
			return true
		}
		// entonces se puede resolver...
		return true
	}
	
	public Task getTaskFor(User u){
		if(this.tasks) {
			/**if (u.skippedRecom.size() == this.getRecommendations().size()) {
				//println "Reseteando las recomendaciones"
				u.skippedRecom.clear()
				}**/
			//println("skippeds: "+u.skippedRecom)
			def r = this.findNextTaskForUser(u)/**this.tasks.find{ Task w -> 
				w.canBeDeliveredFor(u) && !u.skippedRecom.contains(w)
				}**/
			return r
		}
		return null
	}
	
	public setUserXP(Long userID) {
		if (!usersXP) {
			this.usersXP = new LinkedHashMap()
			this.usersXP[(userID)]=0
			//println "gola"
		}
		else
			if (!(this.usersXP[userID])) {
				this.usersXP[(userID)]=0
				//println "gola2"
			}
			
		//println this.usersXP.class
	}
	
	public int getUserXPByID(Long id) {
		/*
		 * Método que dado un usuario retorne un entero que representa la experiencia obtenida del mismo
		 * en el proyecto
		 */
		this.usersXP[(id)]
	}
	
	public addOwner(User owner) {
		if (!owners){
			this.owners = new ArrayList()
		}
		this.owners.add(owner)
		owner.addProjectAsOwned(this)
	}

	public getUsersRanking() {
		this.usersXP.sort { -it.value }
		
	}
	
	def addCollaborator(User user) {
		if (!this.users)
			this.users = new HashSet()
		this.users.add(user)
	}

	/**def issueBadges() {
	
	//def resp = rest.get("https://cientificos-badges-api.herokuapp.com/")
	}
	
	def grantBadge(String userEmail, int idBadge) {
		
		RestBuilder rest = new RestBuilder()
		//def resp = rest.get("https://cientificos-badges-api.herokuapp.com/")
		//println resp.json
		
		def resp = rest.post("https://163.10.5.42/badges/90812gjd/instances") {
			contentType "application/json"
			json {
				email = "${userEmail}"
			}
		}
		
		println resp.json
	}
	
	def getUserBadges(User u) {
		//def badges = User.getEsos(u.id, this.name)
		User.holaMundo()
	
	}**/
		
}
