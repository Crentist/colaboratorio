package colaboratorio

/**
 * InstructionsForm
 * A domain class describes the data object and it's mapping to the database
 */
class InstructionsForm {


	/* Default (injected) attributes of GORM */
//	Long	id
//	Long	version
	
	/* Automatic timestamping of GORM */
//	Date	dateCreated
//	Date	lastUpdated
	
	String instructions
	//int order //order in the workflow -- Order is mantained in Task
	List possibleAnswers //eg. ('Yes','No','Dunno',...) Order is mantained, just like Task mantains its InstructionsForm order
	Boolean multipleChoice //whether or not you can choose multiple options. Essentially, the difference between a RadioBox and a CheckBox
	
	static	belongsTo	= [task:Task]	// tells GORM to cascade commands: e.g., delete this object if the "parent" is deleted.
	static	hasOne		= [resource:Resource]	// tells GORM to associate another domain object as an owner in a 1-1 mapping
	static	hasMany		= [answers: InstructionsAnswer]	// tells GORM to associate other domain objects for a 1-n or n-m mapping
//	static	mappedBy	= []	// specifies which property should be used in a mapping 
	
    static	mapping = {
		instructions column: 'instructions', sqlType: 'varchar(1000)'
    }
    
	static	constraints = {
		answers nullable: true
		resource nullable: true
    }
	
	/*
	 * Methods of the Domain Class
	 */
//	@Override	// Override toString for a nicer / more descriptive UI 
//	public String toString() {
//		return "${name}";
//	}
	
	InstructionsForm(String instructions) {
		this.setInstructions(instructions)
	}
	
	Boolean hasResource() {
		return resource != null
	}
}
