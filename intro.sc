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