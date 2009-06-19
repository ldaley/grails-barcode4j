class BarcodeTagLib {
	def barcode4j = { attrs ->
		def format = 'fmt'
		// at least format(fmt) has to be provided
		if(!attrs[format]){
			throw new Exception("Attribute ${format} is required!")
		}
		out << "<img border=\"0\" src=\"${createLink(controller:'genbc', params:attrs)}\" />" 
	}
}
