// load text files
val input = sc.textFile("path/to/file/README.md")
// average vaue per file
val input = sc.wholeTextFiles("path/to/file")
val result = input.mapValues{y =>
    val nums = y.split(" ").map(x => x.toDouble)
    nums.sum / sunms.size.toDouble
}