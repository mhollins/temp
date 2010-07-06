
def get = new Get(url:"http://musicbrainz.org/ws/1/artist/" )
get.queryString.add("type" , 'xml')
get.queryString.add('name', 'Newsboys')

println get

def metadata = new XmlSlurper().parseText(get.text)

def id
for (artist in metadata.'artist-list'.artist) {
	if (artist.@'ext:score' == 100) {
		id = artist.@id
		break
	}
}

get.url = "http://musicbrainz.org/ws/1/artist/${id}"
get.queryString = new QueryString([type: 'xml', inc: 'sa-Official'])
println get

metadata = new XmlSlurper().parseText(get.text)

metadata.artist.'release-list'.release.each {
	println it.title
}
