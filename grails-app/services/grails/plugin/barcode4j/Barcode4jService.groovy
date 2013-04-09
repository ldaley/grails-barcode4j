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

package grails.plugin.barcode4j

import java.awt.image.BufferedImage
import org.krysalis.barcode4j.BarcodeGenerator
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider

import javax.servlet.http.HttpServletResponse

class Barcode4jService {
	
	def grailsApplication
	
	int defaultDpi = 300
	int defaultImageType = BufferedImage.TYPE_BYTE_BINARY
	boolean defaultAntiAlias = false
	int defaultOrientation = 0
	
	String pngMimeType = "image/png"
	String jpegMimeType = "image/jpeg"
	String gifMimeType = "image/gif"
	
	// Generic render
	
	boolean render(BarcodeGenerator generator, String msg, OutputStream out, String mimeType, Map params = [:]) {
		def mergedParams = mergeWithDefaultParams(params)
		def canvas = new BitmapCanvasProvider(out, mimeType, mergedParams.dpi, mergedParams.imageType, mergedParams.antiAlias, mergedParams.orientation)

		generator.generateBarcode(canvas, msg)
		canvas.finish()
		
		false // returns false so it can be used as the last line in an action
	}
	
	boolean render(String generatorName, String msg, OutputStream out, String mimeType, Map params = [:]) {
		render(getGenerator(generatorName), msg, out, mimeType, params)
	}

	boolean render(BarcodeGenerator generator, String msg, HttpServletResponse response, String mimeType, Map params = [:]) {
		response.contentType = mimeType
		render(generator, msg, response.outputStream, mimeType, params)
	}

	boolean render(String generatorName, String msg, HttpServletResponse response, String mimeType, Map params = [:]) {
		render(getGenerator(generatorName), msg, response.outputStream, mimeType, params)
	}
	
	byte[] render(String generatorName, String msg, String mimeType, Map params = [:]) {
		render(getGenerator(generatorName), msg, mimeType, params)
	}

	byte[] render(BarcodeGenerator generator, String msg, String mimeType, Map params = [:]) {
		def baos = new ByteArrayOutputStream()
		render(generator, msg, baos, mimeType, params)
		baos.toByteArray()
	}
	
	// PNG methods

	boolean png(BarcodeGenerator generator, String msg, OutputStream out, Map params = [:]) {
		render(generator, msg, out, pngMimeType, params)
	}
	
	boolean png(String generatorName, String msg, OutputStream out, Map params = [:]) {
		render(generatorName, msg, out, pngMimeType, params)
	}

	boolean png(BarcodeGenerator generator, String msg, HttpServletResponse response, Map params = [:]) {
		render(generator, msg, response, pngMimeType, params)
	}

	boolean png(String generatorName, String msg, HttpServletResponse response, Map params = [:]) {
		render(generatorName, msg, response, pngMimeType, params)
	}

	byte[] png(String generatorName, String msg, Map params = [:]) {
		render(generatorName, msg, pngMimeType, params)
	}

	byte[] png(BarcodeGenerator generator, String msg, Map params = [:]) {
		render(generator, msg, pngMimeType, params)
	}


	// JPEG Methods

	boolean jpeg(BarcodeGenerator generator, String msg, OutputStream out, Map params = [:]) {
		render(generator, msg, out, jpegMimeType, params)
	}
	
	boolean jpeg(String generatorName, String msg, OutputStream out, Map params = [:]) {
		render(generatorName, msg, out, jpegMimeType, params)
	}

	boolean jpeg(BarcodeGenerator generator, String msg, HttpServletResponse response, Map params = [:]) {
		render(generator, msg, response, jpegMimeType, params)
	}

	boolean jpeg(String generatorName, String msg, HttpServletResponse response, Map params = [:]) {
		render(generatorName, msg, response, jpegMimeType, params)
	}
	
	byte[] jpeg(String generatorName, String msg, Map params = [:]) {
		render(generatorName, msg, jpegMimeType, params)
	}

	byte[] jpeg(BarcodeGenerator generator, String msg, Map params = [:]) {
		render(generator, msg, jpegMimeType, params)
	}
	
	
	// GIF Methods
	
	boolean gif(BarcodeGenerator generator, String msg, OutputStream out, Map params = [:]) {
		render(generator, msg, out, gifMimeType, params)
	}
	
	boolean gif(String generatorName, String msg, OutputStream out, Map params = [:]) {
		render(generatorName, msg, out, gifMimeType, params)
	}

	boolean gif(BarcodeGenerator generator, String msg, HttpServletResponse response, Map params = [:]) {
		render(generator, msg, response, gifMimeType, params)
	}

	boolean gif(String generatorName, String msg, HttpServletResponse response, Map params = [:]) {
		render(generatorName, msg, response, gifMimeType, params)
	}
	
	byte[] gif(String generatorName, String msg, Map params = [:]) {
		render(generatorName, msg, gifMimeType, params)
	}

	byte[] gif(BarcodeGenerator generator, String msg, Map params = [:]) {
		render(generator, msg, gifMimeType, params)
	}
	
	
	// Helpers
	
	protected Map mergeWithDefaultParams(Map params) {
		def merged = [:]
		
		// int types
		[dpi: defaultDpi, imageType: defaultImageType, orientation: defaultOrientation].each { key, defaultValue ->
			if (params.containsKey(key)) {
				def value = params[key]
				if (value instanceof Integer) {
					merged[key] = value
				} else {
					throw new IllegalArgumentException("param '$key' must be an Integer")
				}
			} else {
				merged[key] = defaultValue
			}
		}
		
		// boolean types
		[antiAlias: defaultAntiAlias].each { key, defaultValue ->
			if (params.containsKey(key)) {
				def value = params[key]
				if (value instanceof Boolean) {
					merged[key] = value
				} else {
					throw new IllegalArgumentException("param '$key' must be an Boolean")
				}
			} else {
				merged[key] = defaultValue
			}
		}
		
		merged
	}
	
	protected BarcodeGenerator getGenerator(String generatorName) {
		if (grailsApplication.mainContext.containsBean(generatorName)) {
			def generator = grailsApplication.mainContext[generatorName]
			if (generator instanceof BarcodeGenerator) {
				generator
			} else {
				throw new IllegalArgumentException("bean '$generatorName' is NOT an instance of ${BarcodeGenerator.name} and cannot be used as a barcode generator")
			}
		} else {
			throw new IllegalArgumentException("did not find any beans with name '$generatorName' when looking for barcode generator")
		}
	}
}