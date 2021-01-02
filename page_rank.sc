// assume neighbor list saved as Spark objectFile
val links = sc.objectFile[(String, Seq[String])]("links")
              .partitionBy(new HashPartitioner(100))
              .persist()

// initialize page rank to 1.0
// mapValues resulting RDD will have same partitioner
var ranks = links.mapValues(v => 1.0)

// 10 iters of PageRank algo
// per iteration, have page p send contribution of
// rank(p)/numNeighbors(p) to its neighbors
// set rank to 0.15 + 0.85 * contributionsReceived
for (i <- 0 until 10) {
    val contributions = links.join(ranks).flatMap {
        case (pageId, (links, rank)) =>
            links.map(dest => (dest, rank / links.size))
    }
    ranks = contributions.reduceByKey((x,y) -> x + y)
                         .mapValues(v => 0.15 + 0.85*v)
}

ranks.collect()