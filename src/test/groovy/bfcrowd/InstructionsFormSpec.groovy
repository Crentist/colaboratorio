package bfcrowd

import colaboratorio.InstructionsForm;
import colaboratorio.Resource;
import colaboratorio.Task;
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(InstructionsForm)
class InstructionsFormSpec extends Specification {
	InstructionsForm inst
    def setup() {
    }

    def cleanup() {
    }

    void "test default instance creation"() {
		given: "a single default InstructionsForm instantiation"
		inst = new InstructionsForm("What is your quest?")
		
		expect:
		inst.getInstructions() == "What is your quest?"

		//assertFalse(task.hasContribution())
		//assertFalse(task.hasProject())
		
    }
	
	void "test populated instance creation"() {
		given: "a populated InstructionsForm instantiation"
		def task = new Task()
		inst = new InstructionsForm(instructions: "What is your favourite color?", 
									multipleChoice: false, 
									task: task)
		def possibleAnswers = ["Blue", "No, yellow"]
		Resource resource = new Resource(resourcePath: "holygrail.jpg")
		inst.setPossibleAnswers(possibleAnswers)
		inst.setResource(resource)
		expect:
		inst.getPossibleAnswers() == ["Blue", "No, yellow"]
		inst.hasResource() == true

		//assertFalse(task.hasContribution())
		//assertFalse(task.hasProject())
		
	}
}
