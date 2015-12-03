package colaboratorio

/**
 * ProjectDefinition
 * A domain class describes the data object and it's mapping to the database
 */
class ProjectDefinition {

	
	/* Default (injected) attributes of GORM */
//	Long	id
//	Long	version
	
	/* Automatic timestamping of GORM */
//	Date	dateCreated
//	Date	lastUpdated
	
	int xpValue //Amount of XP obtained by the user per recommendation solved (i.e Contribution) in the project
	int bonusXP //Amount of XP obtained by the user when (s)he meets the required amount of recomendations solved (stored in 'requiredForBonus')
	int requiredContributionsForBonus
	Boolean areTasksRepeatableBetweenUsers = false
	Boolean areTasksRepeatableBySingleUser = false
	int maxTaskRepeats = 1 // -1 = infinite
	
	static	belongsTo	= [project:Project]	// tells GORM to cascade commands: e.g., delete this object if the "parent" is deleted.
//	static	hasOne		= []	// tells GORM to associate another domain object as an owner in a 1-1 mapping
//	static	hasMany		= []	// tells GORM to associate other domain objects for a 1-n or n-m mapping
//	static	mappedBy	= []	// specifies which property should be used in a mapping 
	
    static	mapping = {
    }
    
	static	constraints = {
    }
	
	/*
	 * Methods of the Domain Class
	 */
//	@Override	// Override toString for a nicer / more descriptive UI 
//	public String toString() {
//		return "${name}";
//	}
}
