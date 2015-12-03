package colaboratorio

/**
 * Recommendation
 * A domain class describes the data object and it's mapping to the database
 */
	


class Task {

	/**String property //Obtenida del escenario
	String path  //Featured Path Query a utilizar
	String fromPage 
	String toPage
	Boolean solved = false**/ //DEPRECATED FIELDS
	//Date dateAssigned = new Date(0) // Fecha en que se asignó la recomendación a un colaborador, 
									  // para que no sea vuelta a asignar instantaneamente. DEPRECATED

	/* Default (injected) attributes of GORM */
//	Long	id
//	Long	version
	/**String instructions
	String checkboxMode**/ //DEPRECATED
	
	//String imagePath DEPRECATED
	
	/* Automatic timestamping of GORM */
//	Date	dateCreated
//	Date	lastUpdated
	List instructions
	static 	belongsTo 	= [project:Project]	// tells GORM to cascade commands: e.g., delete this object if the "parent" is deleted.
	//static	hasOne		= []	// tells GORM to associate another domain object as an owner in a 1-1 mapping
	static	hasMany		= [contributions:Contribution, instructions:InstructionsForm]	// tells GORM to associate other domain objects for a 1-n or n-m mapping
//	static	mappedBy	= []	// specifies which property should be used in a mapping 
	
    static	mapping = {
		//instructions column: 'instructions', sqlType: 'varchar(1000)'
    }
    
	static	constraints = {
		//property unique: ['project', 'path', 'fromPage', 'toPage', 'instructions']
		//checkboxMode inList: ["Checkbox", "Radio"]
		contributions nullable: true
		project nullable: true
    }
	
	/*
	 * Methods of the Domain Class
	 */
//	@Override	// Override toString for a nicer / more descriptive UI 
//	public String toString() {
//		return "${name}";
//	}
	
	def addInstructionsForm(String instructionsText) {
	/**InstructionsForm form = new InstructionsForm(instructions: instructionsText)
	if (!instructions)
		this.instructions = new ArrayList()
		if (!order) {
			this.instructions.add(this.instructions.size(), form)
		}
		else {
			this.instructions.add(this.instructions.size(), form)
		}**/
		InstructionsForm form = new InstructionsForm(instructions: instructionsText)
		if (!instructions)
			this.instructions = new ArrayList()
		this.getInstructions().add(form)
					
	}
	def addInstructionsForm(String instructionsText, List possibleAnswers, boolean multipleChoice, String resourcePath) {
		InstructionsForm form = new InstructionsForm(instructions: instructionsText,
													possibleAnswers: possibleAnswers,
													multipleChoice: multipleChoice,
													resource: new Resource(resourcePath: resourcePath),
													task: this)
		if (!instructions)
			this.instructions = new ArrayList()
		this.getInstructions().add(form)
	}
	
	def addInstructionsForm(String instructionsText, int order) {
		/**InstructionsForm form = new InstructionsForm(instructions: instructionsText)
		if (!instructions)
			this.instructions = new ArrayList()
			if (!order) {
				this.instructions.add(this.instructions.size(), form)
			}
			else {
				this.instructions.add(this.instructions.size(), form)
			}**/
		
		InstructionsForm form = new InstructionsForm(instructions: instructionsText)
		if (!instructions)
			this.instructions = new ArrayList()
		if (this.instructions.size() < order)
			this.getInstructions().add(form)
			else
				this.getInstructions().add(order-1, form)
						
	}
	
	def addInstructionsForm(String instructionsText, List possibleAnswers, boolean multipleChoice, int order, String resourcePath) {
		InstructionsForm form = new InstructionsForm(instructions: instructionsText, 
													possibleAnswers: possibleAnswers,
													multipleChoice: multipleChoice, 
													resource: new Resource(resourcePath: resourcePath),
													task: this)
		if (!instructions)
			this.instructions = new ArrayList()
		if (this.instructions.size() < order)
			this.getInstructions().add(form)
			else
				this.getInstructions().add(order-1, form)
	}
	
	def getNumberOfInstructions() {
		if (!this.instructions)
			return 0
		return this.getInstructions().size()
	}
	
	String getInstructionTextAt(int index) {
		if (this.instructions.size() < index)
			return ""
		return this.instructions.get(index-1).getInstructions()
	}
	
	/**def setResource(String path) {
		this.setResource(new Resource(resourcePath: path))
	}**/
	
		
	/**Boolean isSolved(){
		return this.solved
	}**/
	
	/**def setAssigned(){
		this.dateAssigned = new Date()
	}
	
	Boolean setAsSolved() {
		this.solved = true;
		this.date = new Date()
		
	}**/
	
	//Puede volverse al estado unsolved si por ejemplo cambia algo en alguna convención de Wikipedia
	/**Boolean setAsUnsolved() {
		this.solved = false;
		this.date = new Date()
	}**/
	
	//Un pequeño script que indica paso a paso cómo insertar correctamente el link que conecta al par
	/**def getSolutionScript() { DEPRECATED
		//TO-DO
	}**/
	
	
}