grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.dependency.resolution = {
	inherits("global")
	log "warn"

	repositories {
		grailsPlugins()
		grailsHome()
		mavenLocal()
		mavenCentral()
		
		try {
			grailsCentral()
		} catch (MissingMethodException e) {
			// ignore, pre Grails 1.3
		}
	}

	dependencies {
		compile('net.sf.barcode4j:barcode4j-light:2.0') {
			excludes 'xalan'
			excludes 'xml-apis'
		}
	}
	
	plugins {
		test(':spock:0.5-groovy-1.7-SNAPSHOT') {
			exported = false
		}
	}
}
