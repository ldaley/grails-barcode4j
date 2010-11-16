class UrlMappings {
	static mappings = {
		"/$controller/$action?/$id?"()
		"/"(view:"/index")
		"500"(view:'/error')
	}
}
