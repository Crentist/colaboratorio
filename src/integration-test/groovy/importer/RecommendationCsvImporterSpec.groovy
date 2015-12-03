package importer

import grails.test.mixin.TestFor
//import grails.test.mixin.Mock
//import grails.test.mixin.DomainClass
//import grails.test.mixin.domain.DomainClassUnitTestMixin
import spock.lang.Specification
import bfcrowd.*


/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
//@TestFor(RecommendationCsvImporter)
//@Mock([Project, Recommendation, Contribution, User, Profile])
class RecommendationCsvImporterSpec2 extends GroovyTestCase { //Le agregué un 2 al final porque tiraba error de que la clase ya existe. VER @Giuliano

    def setup() {
//		mockDomain(Project)
//		mockDomain(Recommendation)
    }

    def cleanup() {
    }

    void "test importFile"() {
		when:
		def importer = new RecommendationCsvImporter()
		BufferedWriter writer = null;
		try {
			//create a temporary file
			File logFile = new File("web-app/last_import.csv");

			writer = new BufferedWriter(new FileWriter(logFile));
			writer.write('''1project;property;path;fromPage;toPage;Instrucctions;Checkbox
Project1;fff;asdf;fdsa;fdf;fff;Checkbox
Project1;fff2;asdf2 fds fdsf;fdsa2;fdf2;fff2;Checkbox
Project1;fff2;asdf2 fds fdsf;fdsa2;fdf2;fff2;Checkbox
;LLLL;LLLL;LLLL;LLLL;LLLL;Checkbox
Project2;A1;A1;A1;A1;A1;Checkboxdfs
Project2;fff2;asdf2 fds fdsf;fdsa2;fdf2;fff2;Checkbox
;CCC;CCC;CCC;CCC;CCC;Checkbox
Project2;GGGG;GGGG;GGGG;GGGG;GGGG;Checkbox
Project3;propertyAA;pathBB;no?;asdf;asdf;Checkbox''');
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the writer regardless of what happens...
				writer.close();
			} catch (Exception e) {
			}
		}
		importer.importFile()
		
		then:
		println Project.getAll()
		println Project.find { name == "Wikipedia tasks" }
		println Project.find { name == "Project3" }
		Project.findByName("Project3").recommendations.size() == 1
    }
}
