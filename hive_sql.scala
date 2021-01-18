// hive load
import org.apache.spark.sql.hive.HiveContext

val hiveCtx = new HiveContext(sc)
val rows = hiveCtx.sql("
    SELECT key, value
    FROM sample_table
")
val keys = rows.map(row => row.getInt(0))

// crate schemaRDD from case class

case class HappyPerson(handel: String, favouriteBeverage: String)
// create a person and turn it into a SchemaRDD
val happyPeopleRDD = sc.parallelize(List(HappyPerson("holden", "coffee")))
// implicit conversion - sqlCtx.createSchemaRDD(happyPeopleRDD)
happyPeopleRDD.registertempTable("happy_table")

// UDF for text length
registerFunction("strlenScala", (_: String).length)
val tweetLength = hiveCtx.sql("SELECT strLenScala('tweet') FROM tweets LIMIT 10")