class QueryString{
  Map params = [:]
  //this constructor allows you to pass in a Map
  QueryString(Map params){
    if(params){
      this.params.putAll(params)
    }
  }
  //this method allows you to add name/value pairs
  void add(String name, Object value){
    params.put(name, value)
  }
  //this method returns a well-formed QueryString
  String toString(){
    def list = []
    params.each{name,value->
      list << "$name=" + URLEncoder.encode(value.toString())
    }
    return list.join("&" )
  }
}

