package bfcrowd

import colaboratorio.Project;
import colaboratorio.Task;
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class TaskSpec extends Specification {

	Task task
    def setup() {
		
		//task = new Task()
    }

    def cleanup() {
		//task = null
    }

    void "test default instance creation"() {
		given: "a single default Task instantiation"
		task = new Task()
		
		expect: 
		task.getContributions() == null
		task.getProject() == null
		task.getInstructions() == null
		//assertFalse(task.hasContribution())
		//assertFalse(task.hasProject())
    }
	
	void "test populated instance creation"() {
		given: "a single populated Task instantiation"
		Project project = new Project(name: "test project", description: "testcription")
		task = new Task()
		
		expect: "it shouldn't have any related domain instances"
		task.getContributions() == null
		task.getProject() == null
		task.getInstructions() == null
		//assertFalse(task.hasContribution())
		//assertFalse(task.hasProject())
	}
	
	void "test the addition of one instructionsForm at the beginning of the workflow"() {
		given: "a single populated Task instantiation"
		Project project = new Project(name: "test project", description: "testcription")
		task = new Task()
		
		when: "adding a single instruction to the workflow with no order"
		task.addInstructionsForm("Do you even?")
		
		then: "the instruction form should be at the beginning of the workflow (that is, the first element of the collection), and the latter should have size = 1"
		task.getNumberOfInstructions() == 1 //workflowSize()
		task.getInstructionTextAt(1) == "Do you even?" //get(0) = first element

	}
	
	void "test the addition of one instructionsForm at a given order number"() {
		/**
		 * No zero-based order. That is, order starts at 1 despite the fact that ArrayLists are zero-based.
		 * Given an outOfBounds order, the element should be added at the end.
		 * Adding an element at a given position X should displace the current 
		 * element and the following elements by one place (if they exist)
		 */
		given: "a single populated Task instantiation"
		Project project = new Project(name: "test project", description: "testcription")
		task = new Task()
		
		when: "adding a single instruction to the workflow with order 1"
		task.addInstructionsForm("Do you even?", 1)
		
		then: "the instruction form should be at the beginning of the workflow (that is, the first element of the collection), and the latter should have size = 1"
		task.getNumberOfInstructions() == 1 //o puede ser workflowSize()
		task.getInstructionTextAt(1) == "Do you even?" //get(0) = first element
		
		when: "adding a single instruction to the workflow with order 1"
		task.addInstructionsForm("How do you do?", 1)
		
		then: '''the instruction form should be at the beginning of the workflow 
				 (that is, the first element of the collection), and the latter 
				 should have size = 2. The first task ("Do you even?") should be
				 second now'''
		task.getNumberOfInstructions() == 2 //workflowSize()
		task.getInstructionTextAt(1) == "How do you do?" //get(0) = first element
		task.getInstructionTextAt(2) == "Do you even?"
		
		when: "adding a single instruction to the workflow with order 5"
		task.addInstructionsForm("Got Milk?", 5)
		
		then: '''being an OutOfBounds index, it should be added at the end, 
				 without changing the others'''
		task.getNumberOfInstructions() == 3 //workflowSize()
		task.getInstructionTextAt(1) == "How do you do?" //get(0) = first element
		task.getInstructionTextAt(2) == "Do you even?"
		task.getInstructionTextAt(3) == "Got Milk?"
	}
	
	void "test the addition of one instructionsForm with given fields"() {
		given: "a single populated Task instantiation"
		Project project = new Project(name: "test project", description: "testcription")
		task = new Task()
		
		when: "adding a single instruction to the workflow"
								//instructions, possibleAnswers, multipleChoice, order, resourcePath
		task.addInstructionsForm("Do you even?", ["Yes","No"], false, 4, "photo.jpg")
		
		then:
		task.getNumberOfInstructions() == 1 //o puede ser workflowSize()
		task.getInstructionTextAt(1) == "Do you even?" //get(0) = first element
		task.getInstructions().get(0).getPossibleAnswers() == ["Yes","No"]
	}
}
