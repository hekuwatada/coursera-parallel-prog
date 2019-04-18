package w1

/*
  Execute two threads without an overlap
 */
object SynchronizedPrimitiveExample extends App {

  val synchronizedBlock = new AnyRef {}
  var uidCount = 0L

  // code block after a synchronized call on an object (synchronized primitive)
  // is never executed by two threads at the same time.
  def getUniqueId(): Long = synchronizedBlock.synchronized {
    uidCount = uidCount + 1
    uidCount
  }

  def startThread() = {
    val t = new Thread {

      override def run(): Unit = {
        val uids = for (i <- 0 until 10) yield getUniqueId()
        // getId() is only unique during the lifetime of this thread; maybe re-used
        println(s"${getId} $uids")
      }
    }
    t.start()
    t
  }

  val t1 = startThread()
  val t2 = startThread()

  t1.join()
  t2.join()
}
