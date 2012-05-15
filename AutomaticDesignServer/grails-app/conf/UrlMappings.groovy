class UrlMappings {

	static mappings = {
		"/$controller/$id?/$action?"{
			constraints {
				// apply constraints here
			}
		}

                "/$controller/$action?" {
                    constraints {
                        
                    }
                }

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
