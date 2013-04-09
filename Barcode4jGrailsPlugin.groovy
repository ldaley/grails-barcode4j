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

import org.krysalis.barcode4j.BarcodeGenerator

import grails.util.Environment
import grails.util.Metadata

class Barcode4jGrailsPlugin {
	def author = "Luke Daley"
	def authorEmail = "ld@ldaley.com"
	def title = "Barcode4j Plugin"
	def description = 'Provides the generation of barcodes, using the Barcode4j library'
	def documentation = "http://www.grails.org/plugin/barcode4j"
	
	def version = "0.3"
	def grailsVersion = "1.2 > *"
	def dependsOn = [:]
	
	def pluginExcludes = [
		"**/grails/plugin/barcode4j/test/**/*",
		"grails-app/conf/resources.groovy",
		"web-app/**/*"
	]

	def observe = [
		"controllers"
	]
	
	private renderMethods
	
	def doWithDynamicMethods = {
		def methods = createMethods(it.barcode4jService)
		for (controllerClass in application.controllerClasses) {
			attachRenderMethods(controllerClass, methods)
		}
		
		if (isEnvironmentClassReloadable()) {
			// Only create the reference if needed for reloading
			// If we aren't reloading we don't need to hold on to this
			renderMethods = methods
		}
	}
	
	def onChange = { event ->
		if (application.isControllerClass(event.source)) {
			attachRenderMethods(event.source, renderMethods)
		}
	}
	
	protected attachRenderMethods(controllerClass, methods) {
		methods.each { name, impls ->
			impls.each { impl ->
				controllerClass.metaClass."$name" = impl
			}
		}
	}
	
	private createMethods(barcode4jService) {
		def renderPng = { String generatorName, String message, Map params = [:] ->
			barcode4jService.png(generatorName, message, delegate.response, params)
		}

		def renderPngByName = { BarcodeGenerator generator, String message, Map params = [:] ->
			barcode4jService.png(generator, message, delegate.response, params)
		}

		def renderJpeg = { String generatorName, String message, Map params = [:] ->
			barcode4jService.jpeg(generatorName, message, delegate.response, params)
		}

		def renderJpegByName = { BarcodeGenerator generator, String message, Map params = [:] ->
			barcode4jService.jpeg(generator, message, delegate.response, params)
		}

		def renderGif = { String generatorName, String message, Map params = [:] ->
			barcode4jService.gif(generatorName, message, delegate.response, params)
		}

		def renderGifByName = { BarcodeGenerator generator, String message, Map params = [:] ->
			barcode4jService.gif(generator, message, delegate.response, params)
		}

		return [
			renderBarcodePng: [renderPng, renderPngByName],
			renderBarcodeJpeg: [renderJpeg, renderJpegByName],
			renderBarcodeGif: [renderGif, renderGifByName]
		]
	}
	
	
	static private isEnvironmentClassReloadable() {
		def env = Environment.current
		env.reloadEnabled || (Metadata.current.getApplicationName() == "barcode4j" && env == Environment.TEST)
	}
}