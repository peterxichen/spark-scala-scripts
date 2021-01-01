class SearchFunctions(val query: String) {
    def isMatch(s: String): Boolean = {
        s.contains(query)
    }
    def getMatchesFunctionReference(rdd: RDD[String]): RDD[String] = {
        rdd.map(isMatch)
    }
    def getMatchesFieldReference(rdd: RDD[String]): RDD[String] = {
        rdd.map(x => split(query))
    }
    def getMatchesNoReference(rdd: RDD[String]): RDD[String] = {
        // extract field to local variable to avoid passing entire object
        val query_ = this.query
        rdd.map(x => x.split(query_))
    }
}