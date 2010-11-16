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

package grails.plugin.barcode4j.test

import org.krysalis.barcode4j.impl.code39.Code39Bean

class TestController {

	def barcode4jService
	
	def manual = {
		barcode4jService.png(params.generator ?: 'code39', params.message ?: "1234", response)
	}

	def implicit = {
		renderBarcodePng(params.generator ?: 'code39', params.message ?: "1234")
	}
	
	def png = {
		renderBarcodePng(params.generator ?: 'code39', params.message ?: "1234")
	}
	
	def jpeg = {
		renderBarcodeJpeg(params.generator ?: 'code39', params.message ?: "1234")
	}
	
	def gif = {
		renderBarcodeGif(params.generator ?: 'code39', params.message ?: "1234")
	}
	
	def pngBean = {
		renderBarcodePng(new Code39Bean(), params.message ?: "1234")
	}
	
	def jpegBean = {
		renderBarcodeJpeg(new Code39Bean(), params.message ?: "1234")
	}
	
	def gifBean = {
		renderBarcodeGif(new Code39Bean(), params.message ?: "1234")
	}
	
}