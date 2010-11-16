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