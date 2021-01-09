// load text files
val input = sc.textFile("path/to/file/README.md")
// average vaue per file
val input = sc.wholeTextFiles("path/to/file")
val result = input.mapValues{y =>
    val nums = y.split(" ").map(x => x.toDouble)
    nums.sum / sunms.size.toDouble
}
// saving text
result.saveAsTextFile(outputFile)

// loading JSON
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.DeserializationFeature

case class Person(name: String, flag: Boolean)

val result = input.flatMap(record => {
    try {
        Some(mapper.readValue(ecord, classOf[Person]))
    } catch {
        case e: Exception => None
    }
})
// saving JSON
result.filter(p => P.flag).map(mapper.writeValuesAsString(_))
      .saveAsTextFile(outputFile)