/*
 * Copyright 2010 Luke Daley
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

grails.servlet.version = "2.5"
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6

grails.project.dependency.resolution = {
	inherits("global")
	log "warn"

	repositories {
        inherits true
        grailsPlugins()
        grailsHome()
        grailsCentral()
        mavenLocal()
        mavenCentral()
	}

	dependencies {
		compile('net.sf.barcode4j:barcode4j:2.1') {
			excludes 'xalan'
			excludes 'xml-apis'
		}
	}
	
	plugins {
		compile ":hibernate:$grailsVersion", ":tomcat:$grailsVersion", {
			export = false
		}
		build ":release:2.0.4", {
            export = false
            exclude "groovy"
        }
		test ':spock:0.7', {
			export = false
		}
	}
}

grails.release.scm.enabled = false
