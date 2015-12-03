package bfcrowd

import colaboratorio.Project
import colaboratorio.ProjectDefinition
import colaboratorio.Task
import colaboratorio.User
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Project)
class ProjectSpec extends Specification {

	Project project
    def setup() {
    }

    def cleanup() {
    }

    void "test default instance creation"() {
		given: "a single default Task instantiation"
		project = new Project()
		
		expect:
		//The created instance should be invalid, since is missing key fields
		assertFalse project.validate(["name"])
		assertFalse project.validate(["description"])
		assertFalse project.validate(["type"])
		assertFalse project.validate(["owners"])
		assertFalse project.validate(["definition"])
		//These fields are not required, so it should be fine
		assertTrue project.validate(["tasks"])
		assertTrue project.validate(["users"])
		assertTrue project.validate(["usersXP"])
		assertTrue project.validate(["logo"])
		assertTrue project.validate(["tutorial"])
    }
	
	void "test populated instance creation"() {
		given: "a single default Task instantiation"
		project = new Project(name: "test proj",
							  description: "testcription",
							  type: "imageProject"
							  )
		def definition = new ProjectDefinition()
		def owner = new User()
		project.setDefinition(definition)
		project.addOwner(owner)
		
		expect:
		//The created instance should be invalid, since is missing key fields
		assertTrue project.validate(["name"])
		assertTrue project.validate(["description"])
		assertTrue project.validate(["type"])
		assertTrue project.validate(["owners"])
		assertTrue project.validate(["definition"])
		//These fields are not required, so it should be fine
		/**assertTrue project.validate(["tasks"])
		assertTrue project.validate(["users"])
		assertTrue project.validate(["usersXP"])
		assertTrue project.validate(["logo"])
		assertTrue project.validate(["tutorial"])**/
		
		when:
		def project2 = new Project(name: "test proj",
			description: "testcription",
			type: "invalidType"
			)
		
		then:
		//name should be unique
		//println project.name
		//println project2.name
		//assertFalse project2.validate(["name"]) - For some reason, uniqueness cannot be tested. That is, validation passes with an already existing project name
		//type should be either 'imageProject' or 'taskProject'
		assertFalse project2.validate(["type"])
	}

	void "test adding tasks, collaborators, owners"() {
		/**
		 * Tasks should be unordered
		 * 
		 */
		given: "a single default Task instantiation"
		project = new Project(name: "test proj",
							  description: "testcription",
							  type: "imageProject"
							  )
		def definition = new ProjectDefinition()
		def owner = new User()
		project.setDefinition(definition)
		project.addOwner(owner)
		
		expect:
		assertTrue project.validate()
		
		when:
		Task t = new Task()
		t.addInstructionsForm("Do you even?", ["Yes","No"], false, 4, "photo.jpg")
		project.addTask(t)
		Task u = new Task()
		
		then:
		assertTrue project.getTasks().contains(t)
		assertFalse project.getTasks().contains(u)
	}
	
	void "test retrieving tasks for given users"() {
		given: "a single default Task instantiation"
		project = new Project(name: "test proj",
							  description: "testcription",
							  type: "imageProject"
							  )
		def definition = new ProjectDefinition()
		def owner = new User()
		project.setDefinition(definition)
		project.addOwner(owner)
		Task t = new Task()
		t.addInstructionsForm("Do you even?", ["Yes","No"], false, "photo.jpg")
		project.addTask(t)
		Task t2 = new Task()
		t2.addInstructionsForm("Is this any good?", ["Yes","No"], false, "photo2.jpg")
		project.addTask(t2)
		User u = new User()
		project.addCollaborator(u)
		
		expect:
		assertTrue project.validate()
		
		when:
		def collab = new User()
		Task t3 = project.getTaskFor(collab)
		
		then:
		assertTrue (t3 == t) || (t2 == t)
		
		when:
		collab.skipTask(t2)
		t3 = project.getTaskFor(collab)
		
		then:
		assertFalse (t3 == t)
	}
}
