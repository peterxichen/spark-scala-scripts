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
// saving SequenceFile
val data = sc.parallelize(List(("Panda", 3), ("Kay", 6), ("Snail", 2)))
data.saveAsSequenceFile(outputFile)

// KeyValueTextInputFormat()
val input = sc.hadoopFile[Text, Text, KeyValueTextInputFormat](inputFile).map{
case (x, y) => (x.toString, y.toString)
}

// LZO-compressed JSON
val input = sc.newAPIHadoopFile(inputFile, classOf[LzoJsonInputFormat],
    classOf[LongWritable], classOf[MapWritable], conf)
)

// Hive
import org.apache.spark.sql.hive.HiveContext
val hiveCtx = new org.apache.spark.sql.hive.HiveContext(sc)
val rows = hiveCtx.sql("SELECT * FROM table")
val firstRoe = rows.first()
println(firstRow.getString(0)) // return 0th field
//json loading
tweets = hiveCtx.jsonFile("tweets.json")
tweets.registerTempTable("tweets")
results = hiveCtx.sql("SELCT * FROM tweets")

// JDBC - MySQL, Postgres etc
def createConnection() = {
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    DriverManager.getConnection("jdbc:mysql://localhost/test?user=holden");
}
def extractValues(r: ResultSet) = {
    (r.getInt(1), r.getString(2))
}
val data = new JdbcRDD(sc,
    createConnection, "SELECT * FROM panda WHERE ? <= id AND id <= ?",
    lowerBound = 1, upperBound = 3, numPartitions = 2, mapRow = extractValues)
println(data.collect().toList)

// Cassandra
val conf = new SparkConf(true)
            .set("spark.cassandra.connection.host", "hostname")
val sc = new SparkContext(conf)
import com.datastax.spark.connector._ // add functions to sc
val data = sc.cassandraTable("test", "kv") // create table test.kv
data.map(row => row.getInt("value")).stats()

// HBase
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.Result
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
val conf = HBaseConfiguration.create()
conf.set(TableInputFormat.INPUT_TABLE, "tablename") // which table to scan
val rdd = sc.newAPIHadoopRDD(
    conf, classOf[TableInputFormat], classOf[ImmutableBytesWritable], classOf[Result])

// Elasticsearch
def mapWritableToInput(in: MapWritable): Map[String, String] = {
    in.map{case (k, v) => (k.toString, v.toString)}.toMap
}
val jobConf = new JobConf(sc.hadoopConfiguration)
jobConf.set(ConfigurationOptions.ES_RESOURCE_READ, args(1))
jobConf.set(ConfigurationOptions.ES_NODES, args(2))
val currentTweets = sc.hadoopRDD(jobConf,
    classOf[EsInputFormat[Object, MapWritable]], classOf[Object],
    classOf[MapWritable])
// Extract only the map
// Convert the MapWritable[Text, Text] to Map[String, String]
val tweets = currentTweets.map{ case (key, value) => mapWritableToInput(value) }

// per-partition map and foreach
val tempLists = validSigns.distinct().mapPartitions{
    signs =>
    val mapper = createMapper()
    val client = new HttpClient()
    client.start()
    signs.map {sign =>
        createExchangeForSign(sign)
    }.map{ case (sign, exchange) =>
        (sign, readExchangeCallLog(mapper, exchange))
    }.filter(x => x._2 != null) // remove empty
}

// pipe method from R
val distScript = "./src/R/finddistance.R"
val distScriptName = "finddistance.R"
sc.addFile(distScript)
val distances = contactsContactLists.values.flatMap(x => x.map(y =>
    s"$y.contactlay,$y.contactlong,$y.mylat,$y.mylong")).pipe(Seq(
        SparkFiles.get(distScriptName)))
println(distances.collect().toList)