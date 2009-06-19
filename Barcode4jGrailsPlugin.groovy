class Barcode4jGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.1.1 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def author = "Tomasz Bandura"
    def authorEmail = "tomasz.bandura@gmail.com"
    def title = "Barcode4j plugin"
    def description = '''\\
This plugin provides functionality of generating barcodes.
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/Barcode4j+Plugin"

    def doWithSpring = {
        // TODO Implement runtime spring config (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
    }

    def doWithWebDescriptor = { xml ->
		def servlets = xml.'servlet'
    	servlets[servlets.size()-1] + {
    		  'servlet' {
    			  'servlet-name'("BarcodeServlet")
    			  'servlet-class'("org.krysalis.barcode4j.servlet.BarcodeServlet")  
    		  }
		  }
		def mappings = xml.'servlet-mapping'
    	mappings[mappings.size()-1] + {
    		'servlet-mapping' {
    			'servlet-name'("BarcodeServlet")
    			'url-pattern'("/gensvg")
    		}
    		'servlet-mapping' {
    			'servlet-name'("BarcodeServlet")
    			'url-pattern'("/genbc")
    		}
    	}
        // TODO Implement additions to web.xml (optional)
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }
}
