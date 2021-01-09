// protocol buffer for remote procedue calls (RPC)
val job = new Job()
val conf = job.getConfiguration
LzoProtobufBlockOutputFormat.setClassCong(classOf[Places.Venue], conf);
val dnaLounge = Places.Venue.newBuilder()
dnaLounge.setId(1);
dnaLounge.setName("DNA Lounge")
dnaLounge.setType(Places.Venue.VanueType.CLUB)
val data = sc.parallelize(List(dnaLounge.build()))

val outputData = data.map { pb =>
    val protoWriteable = ProtobufWritable.newInstane(classOf[Places.Venue]);
    protoWritable.set(pb)
    (null, protoWritable)
}
outputData.saveAsNewAPIHadoopFile(outputFile, classOf[Text],
    classOf[ProtobufWritable[Places.Venue]],
    classOf[LzoProtobufBlockOutputFormat[ProtobufWritable[Places.Venue]]],
    conf
)