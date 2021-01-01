sc // examine spark context

val lines = sc.textFile("README.md")
val pythonLines = lines.filter(line => line.contains("Python"))
pythonLines.first()

// initializing SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

val conf = new SparkConf().setMaster("local").setAppName("My App")
val sc = SparkContext.getOrCreate()
sc.stop()

// word count example
val conf = new SparkConf().setAppName("wordCount")
val sc = new SparkContext(conf)
val input = sc.textFile("README.md")
// Split it up into words.
val words = input.flatMap(line => line.split(" "))
// Transform into pairs and count.
val counts = words.map(word => (word, 1)).reduceByKey{case (x, y) => x + y}

// parallelize
val lines = sc.parallelize(List("pandas", "i like pandas"))

// filter
val inputRDD = sc.textFile("log.txt")
val errorsRDD = inputRDD.filter(line => line.contains("error"))

// take - collects number of elements from RDD
println("Input had " + badLinesRDD.count() + " concerning lines")
println("Here are 10 examples:")
badLinesRDD.take(10).foreach(println) 

// squaring values
val input = sc.parallelize(List(1, 2, 3, 4))
val result = input.map(x => x * x)
println(result.collect().mkString(","))

// flatMap
val lines = sc.parallelize(List("hello world", "hi"))
val words = lines.flatMap(line => line.split(" "))
words.collect().foreach(println)

val sum = rdd.reduce((x,y) => x + y)
val sum = rdd.fold(0)((x,y) => x + y)

// find average
// 1st function: find sum and count
// 2nd function: merge accumulators
val result = input.aggregate((0, 0))(
    (acc, value) => (acc._1 + value, acc._2 + 1),
    (acc1, acc2) => (acc1._1 + acc2._1, acc1._2 + acc2._2))
println(result._1)
print(result._2)
val avg = result._1 / result._2

// use persist to avoid double execution
val result = input.map(x => x*x)
result.persist(StorageLevel.DISK_ONLY)
println(result.count())
println(result.collect().mkString(","))