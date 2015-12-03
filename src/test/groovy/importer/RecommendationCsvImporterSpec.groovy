package importer

import grails.test.mixin.TestFor
//import grails.test.mixin.Mock
//import grails.test.mixin.DomainClass
//import grails.test.mixin.domain.DomainClassUnitTestMixin
import spock.lang.Specification
import colaboratorio.*


/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
//@TestFor(RecommendationCsvImporter)
class RecommendationCsvImporterSpec extends Specification {

    def setup() {
		def project1 = new Project(name: "Project1", description: "Help us improve the contents on Wikipedia!", xpValue: 25)
		def project2 = new Project(name: "Project2", description: "Help us improve the contents on Wikipedia!", xpValue: 25)
		def project3 = new Project(name: "Project3", description: "Help us improve the contents on Wikipedia!", xpValue: 25)
		mockDomain(Project, [project1,project2,project3])
		mockDomain(Task)
    }

    def cleanup() {
    }

    void "test importFile with a normal file"() {
		when:
		def importer = new RecommendationCsvImporter()
		BufferedWriter writer = null;
		try {
			//create a temporary file
			File logFile = new File("web-app/last_import.csv");
			
			writer = new BufferedWriter(new FileWriter(logFile));
			writer.write('''Project1;P1property1;P1path1;P1fromPage1;P1toPage1;P1instructions2;Checkbox
Project1;P1property2;P1path2;P1fromPage2;P1toPage2;P1instructions2;Checkbox
Project1;P1property3;P1path3;P1fromPage3;P1toPage3;P1instructions3;Checkbox
Project1;P1property4;P1path4;P1fromPage4;P1toPage4;P1instructions4;Checkbox
Project2;P2property1;P2path1;P2fromPage1;P2toPage1;P2instructions1;Radio
Project2;P2property2;P2path2;P2fromPage2;P2toPage2;P2instructions2;Radio
Project2;P2property3;P2path3;P2fromPage3;P2toPage3;P2instructions3;Radio
Project3;P3property1;P3path1;P3fromPage1;P3toPage1;P3instructions1;Radio
Project3;P3property2;P3path2;P3fromPage2;P3toPage2;P3instructions2;Radio
''');
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the writer regardless of what happens...
				writer.close();
			} catch (Exception e) {
			}
		}
		def result = importer.importFile()
		
		then:
		result.passed == 9
		result.errors.size() == 0
		Project.findByName("Project1").recommendations.size() == 4
		Project.findByName("Project2").recommendations.size() == 3
		Project.findByName("Project3").recommendations.size() == 2
    }
	
	
	void "test importFile with less fields"() {
		when:
		def importer = new RecommendationCsvImporter()
		BufferedWriter writer = null;
		try {
			//create a temporary file
			File logFile = new File("web-app/last_import.csv");
			
			writer = new BufferedWriter(new FileWriter(logFile));
			writer.write('''Project1;P1property1;P1path1;P1fromPage1;P1toPage1;P1instructions2;Checkbox
Project1;P1property2;P1path2;P1fromPage2;P1toPage2;P1instructions2
Project1;P1property3;P1path3;P1fromPage3;P1toPage3;P1instructions3;Checkbox
Project1;P1property4;P1path4;P1fromPage4;P1toPage4;P1instructions4;Checkbox
Project2;P2property1;P2path1;P2fromPage1;P2toPage1;P2instructions1;Radio
Project2;P2property2;P2path2;P2fromPage2;P2toPage2;P2instructions2;Radio
Project2;P2property3;P2path3;P2fromPage3;P2toPage3;P2instructions3;Radio
Project3;P3property1;P3path1;P3fromPage1;P3toPage1;P3instructions1;Radio
Project3;P3property2;P3path2;P3fromPage2;P3toPage2;P3instructions2;Radio
''');
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the writer regardless of what happens...
				writer.close();
			} catch (Exception e) {
			}
		}
		def result = importer.importFile()
		
		then:
		result.passed == 0
		result.errors.size() == 1
		Task.getAll().size() == 0
	}
	
	void "test importFile with more fields"() {
		when:
		def importer = new RecommendationCsvImporter()
		BufferedWriter writer = null;
		try {
			//create a temporary file
			File logFile = new File("web-app/last_import.csv");
			
			writer = new BufferedWriter(new FileWriter(logFile));
			writer.write('''Project1;P1property1;P1path1;P1fromPage1;P1toPage1;P1instructions2;Checkbox
Project1;P1property2;P1path2;P1fromPage2;P1toPage2;P1instructions2
Project1;P1property3;P1path3;P1fromPage3;P1toPage3;P1instructions3;Checkbox
Project1;P1property4;P1path4;P1fromPage4;P1toPage4;P1instructions4;Checkbox
Project2;P2property1;P2path1;P2fromPage1;P2toPage1;P2instructions1;Radio
Project2;P2property2;P2path2;P2fromPage2;P2toPage2;P2instructions2;Radio
Project2;P2property3;P2path3;P2fromPage3;P2toPage3;P2instructions3;Radio
Project3;P3property1;P3path1;P3fromPage1;P3toPage1;P3instructions1;Radio
Project3;P3property2;P3path2;P3fromPage2;P3toPage2;P3instructions2;Radio
''');
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the writer regardless of what happens...
				writer.close();
			} catch (Exception e) {
			}
		}
		def result = importer.importFile()
		
		then:
		result.passed == 0
		result.errors.size() == 1
		Task.getAll().size() == 0
	}
	
	void "test importFile with blank lines"() {
		when:
		def importer = new RecommendationCsvImporter()
		BufferedWriter writer = null;
		try {
			//create a temporary file
			File logFile = new File("web-app/last_import.csv");
			
			writer = new BufferedWriter(new FileWriter(logFile));
			writer.write('''
Project1;P1property1;P1path1;P1fromPage1;P1toPage1;P1instructions2;Checkbox

Project1;P1property3;P1path3;P1fromPage3;P1toPage3;P1instructions3;Checkbox
Project1;P1property4;P1path4;P1fromPage4;P1toPage4;P1instructions4;Checkbox
Project2;P2property1;P2path1;P2fromPage1;P2toPage1;P2instructions1;Radio
Project2;P2property2;P2path2;P2fromPage2;P2toPage2;P2instructions2;Radio
Project2;P2property3;P2path3;P2fromPage3;P2toPage3;P2instructions3;Radio
Project3;P3property1;P3path1;P3fromPage1;P3toPage1;P3instructions1;Radio
Project3;P3property2;P3path2;P3fromPage2;P3toPage2;P3instructions2;Radio

''');
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the writer regardless of what happens...
				writer.close();
			} catch (Exception e) {
			}
		}
		def result = importer.importFile()
		
		then:
		result.passed == 0
		result.errors.size() == 1
		Task.getAll().size() == 0
	}
	
	void "test importFile with scaped delimiters and cut them"() {
		when:
		def importer = new RecommendationCsvImporter()
		BufferedWriter writer = null;
		try {
			//create a temporary file
			File logFile = new File("web-app/last_import.csv");
			
			writer = new BufferedWriter(new FileWriter(logFile));
			writer.write('''Project1;P1property1;P1path1;P1fromPage1;P1toPage1;P1instructions2;Checkbox
Project1;P1property2;P1path2;P1fromPage2;P1toPage2;P1instructions2;Checkbox
Project1;P1property3;P1path3;"P1 ; P1fromPage3";P1toPage3;P1instructions3;Checkbox
Project1;P1property4;P1path4;""P1 " ; "P1fromPage4"";P1toPage4;P1instructions4;Checkbox
Project2;P2property1;P2path1;P2fromPage1;P2toPage1;P2instructions1;Radio
Project2;P2property2;P2path2;P2fromPage2;P2toPage2;P2instructions2;Radio
Project2;P2property3;P2path3;P2fromPage3;P2toPage3;P2instructions3;Radio
Project3;P3property1;P3path1;P3fromPage1;P3toPage1;P3instructions1;Radio
Project3;P3property2;P3path2;P3fromPage2;P3toPage2;P3instructions2;Radio
''');
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the writer regardless of what happens...
				writer.close();
			} catch (Exception e) {
			}
		}
		def result = importer.importFile()
		
		then:
		Task.get(2).fromPage == "P1fromPage2"
		Task.get(3).fromPage == "P1 ; P1fromPage3"
		Task.get(4).fromPage == "\"P1 \" ; \"P1fromPage4\""
		result.passed == 9
		result.errors.size() == 0
		Project.findByName("Project1").recommendations.size() == 4
		Project.findByName("Project2").recommendations.size() == 3
		Project.findByName("Project3").recommendations.size() == 2
	}
	
	void "test importFile with non existent project"() {
		when:
		def importer = new RecommendationCsvImporter()
		BufferedWriter writer = null;
		try {
			//create a temporary file
			File logFile = new File("web-app/last_import.csv");
			
			writer = new BufferedWriter(new FileWriter(logFile));
			writer.write('''Project1;P1property1;P1path1;P1fromPage1;P1toPage1;P1instructions2;Checkbox
Project1;P1property2;P1path2;P1fromPage2;P1toPage2;P1instructions2;Checkbox
Project1;P1property3;P1path3;P1fromPage3;P1toPage3;P1instructions3;Checkbox
Project1;P1property4;P1path4;P1fromPage4;P1toPage4;P1instructions4;Checkbox
Proje;P2property1;P2path1;P2fromPage1;P2toPage1;P2instructions1;Radio
Project2;P2property2;P2path2;P2fromPage2;P2toPage2;P2instructions2;Radio
Project2;P2property3;P2path3;P2fromPage3;P2toPage3;P2instructions3;Radio
Proje;P3property1;P3path1;P3fromPage1;P3toPage1;P3instructions1;Radio
Project3;P3property2;P3path2;P3fromPage2;P3toPage2;P3instructions2;Radio
''');
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the writer regardless of what happens...
				writer.close();
			} catch (Exception e) {
			}
		}
		def result = importer.importFile()
		
		then:
		result.passed == 7
		result.errors.size() == 2
		Project.findByName("Project1").recommendations.size() == 4
		Project.findByName("Project2").recommendations.size() == 2
		Project.findByName("Project3").recommendations.size() == 1
	}
	
	void "test importFile with blank or wrong checkboxMode field"() {
		when:
		def importer = new RecommendationCsvImporter()
		BufferedWriter writer = null;
		try {
			//create a temporary file
			File logFile = new File("web-app/last_import.csv");
			
			writer = new BufferedWriter(new FileWriter(logFile));
			writer.write('''Project1;P1property1;P1path1;P1fromPage1;P1toPage1;P1instructions2;Checkbox
Project1;P1property2;P1path2;P1fromPage2;P1toPage2;P1instructions2;Checkbox
Project1;P1property3;P1path3;P1fromPage3;P1toPage3;P1instructions3;""
Project1;P1property4;P1path4;P1fromPage4;P1toPage4;P1instructions4;Checkbox
Project2;P2property1;P2path1;P2fromPage1;P2toPage1;P2instructions1;Radio
Project2;P2property2;P2path2;P2fromPage2;P2toPage2;P2instructions2;XXXX
Project2;P2property3;P2path3;P2fromPage3;P2toPage3;P2instructions3;Radio
Project3;P3property1;P3path1;P3fromPage1;P3toPage1;P3instructions1;Radio
Project3;P3property2;P3path2;P3fromPage2;P3toPage2;P3instructions2;Radio
''');
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the writer regardless of what happens...
				writer.close();
			} catch (Exception e) {
			}
		}
		def result = importer.importFile()
		
		then:
		result.passed == 7
		result.errors.size() == 2
		Project.findByName("Project1").recommendations.size() == 3
		Project.findByName("Project2").recommendations.size() == 2
		Project.findByName("Project3").recommendations.size() == 2
	}
	
	
	void "test importFile repeating recommendations"() {
		when:
		def importer = new RecommendationCsvImporter()
		BufferedWriter writer = null;
		try {
			//create a temporary file
			File logFile = new File("web-app/last_import.csv");
			
			writer = new BufferedWriter(new FileWriter(logFile));
			writer.write('''Project1;P1property1;P1path1;P1fromPage1;P1toPage1;P1instructions2;Checkbox
Project1;P1property2;P1path2;P1fromPage2;P1toPage2;P1instructions2;Checkbox
Project1;P1property3;P1path3;P1fromPage3;P1toPage3;P1instructions3;Checkbox
Project1;P1property4;P1path4;P1fromPage4;P1toPage4;P1instructions4;Checkbox
Project1;P1property4;P1path4;P1fromPage4;P1toPage4;P1instructions4;Radio
Project2;P2property1;P2path1;P2fromPage1;P2toPage1;P2instructions1;Radio
Project2;P2property2;P2path2;P2fromPage2;P2toPage2;P2instructions2;Radio
Project2;P2property3;P2path3;P2fromPage3;P2toPage3;P2instructions3;Radio
Project2;P2property3;P2path3;P2fromPage3;P2toPage3;P2instructions3;Radio
Project3;P3property1;P3path1;P3fromPage1;P3toPage1;P3instructions1;Radio
Project3;P3property2;P3path2;P3fromPage2;P3toPage2;P3instructions2;Radio
Project3;P3property2;P3path2;P3fromPage2;P3toPage2;P3instructions2;Radio
''');
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the writer regardless of what happens...
				writer.close();
			} catch (Exception e) {
			}
		}
		def result = importer.importFile()
		
		then:
		result.passed == 9
		result.errors.size() == 3
		Project.findByName("Project1").recommendations.size() == 4
		Project.findByName("Project2").recommendations.size() == 3
		Project.findByName("Project3").recommendations.size() == 2
	}
	
	void "test importFile with null fields"() {
		when:
		def importer = new RecommendationCsvImporter()
		BufferedWriter writer = null;
		try {
			//create a temporary file
			File logFile = new File("web-app/last_import.csv");
			
			writer = new BufferedWriter(new FileWriter(logFile));
			writer.write('''Project1;P1property1;P1path1;P1fromPage1;P1toPage1;P1instructions2;Checkbox
Project1;P1property2;P1path2;P1fromPage2;P1toPage2;P1instructions2;Checkbox
Project1;P1property3;P1path3;P1fromPage3;P1toPage3;P1instructions3;Checkbox
;P1property4;P1path4;P1fromPage4;P1toPage4;P1instructions4;Checkbox
Project2;P2property1;P2path1;P2fromPage1;P2toPage1;P2instructions1;Radio
Project2;P2property2;P2path2;P2fromPage2;P2toPage2;P2instructions2;Radio
Project2;;P2path3;P2fromPage3;P2toPage3;P2instructions3;Radio
Project3;P3property1;P3path1;P3fromPage1;P3toPage1;P3instructions1;Radio
Project3;P3property2;P3path2;P3fromPage2;P3toPage2;;Radio
''');
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the writer regardless of what happens...
				writer.close();
			} catch (Exception e) {
			}
		}
		def result = importer.importFile()
		
		then:
		result.passed == 6
		result.errors.size() == 3
		Project.findByName("Project1").recommendations.size() == 3
		Project.findByName("Project2").recommendations.size() == 2
		Project.findByName("Project3").recommendations.size() == 1
	}
}
