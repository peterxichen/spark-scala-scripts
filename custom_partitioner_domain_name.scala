class DomainNamePartitioner(numParts: Int) extends Partitioner {
    override def numPartitons: Int = numParts
    override def getParittion(key: Any): Int = {
        val domain = new Java.net.URL(key.toString).getHost()
        val code = (domain.hashCode % numPartitions)
        if (code < 0) {
            code + numPartitions
        } else {
            code
        }
    }
    override def equals(other: Any): Boolean = other match {
        case dnp: DomainNamePartitioner =>
            dnp.numPartitions == numPartitions
        case _ =>
            false
    }
}