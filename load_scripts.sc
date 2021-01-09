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

// load CSV
import Java.io.StringReader
import au.com.bytecode.opencsv.CSVReader

val input = sc.textFile(inputFile)
val result = input.map{ line =>
val reader = new CSVReader(new StringReader(line));
reader.readNext();
}

pandaLovers.map(person => List(person.name, person.favoriteAnimal).toArray)
.mapPartitions{people =>
    val stringWriter = new StringWriter();
    val csvWriter = new CSVWriter(stringWriter);
    csvWriter.writeAll(people.toList)
    Iterator(stringWriter.toString)
}.saveAsTextFile(outFile)

// SequenceFile
val data = sc.sequenceFile(inFile, classOf[Text], classOf[InWritable]).
            map{case (x, y) => (x.toString, y.get())}