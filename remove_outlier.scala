// remove outlier using RDD stats() operation
val distanceDouble = distance.map(string => string.toDouble)
val stats = distanceDoubles.stats()
val stddev = stats.stddev
val mean = stats.mean
val reasonableDist = distanceDoubles.filter(x => math.abs(x-mean) < 3*stddev)
println(reasonableDist.collect().toList)