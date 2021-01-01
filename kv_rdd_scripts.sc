// first word as key
val lines = sc.textFile("README.md")
val pairs = lines.map(x => (x.split(" ")(0), x))