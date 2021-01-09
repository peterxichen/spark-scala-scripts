val sc = new SparkContext()
val = file = sc.textFile("file.txt")

val blankLines = sc.accumulator(0)

val callSigns = file.flatMap(lines => {
    if (line == "") {
        blankLines += 1
    }
    line.split(" ")
})

callSigns.saveAsTextFile("output.txt")
println(blankLines.value + " blank lines")