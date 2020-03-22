package scau.lzl.flink.kafka2kudu

case class Tracker(id: Long, uid: Long, timestamp: Long, referer: String, position: Int, entry: String, exit: String) {}
