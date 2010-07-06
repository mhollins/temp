class Get{
  String url
  QueryString queryString = new QueryString()
  URLConnection connection
  String text
  long lasttime = 0
  String getText(){
    def newtime = new Date().time
    def timesincelast = newtime - lasttime
    if (timesincelast < 60000) {
		println "sleeping ${60000 - timesincelast}"
		Thread.sleep(60000 - timesincelast)
    }
    lasttime = new Date().time
    def thisUrl = new URL(this.toString())
    connection = thisUrl.openConnection()
    if(connection.responseCode == 200){
      return connection.content.text
    } else{
      return "Something bad happened\n" +
        "URL: " + this.toString() + "\n" +
        connection.responseCode + ": " +
        connection.responseMessage
    }
  }
  String toString(){
    return url + "?" + queryString.toString()
  }
}

