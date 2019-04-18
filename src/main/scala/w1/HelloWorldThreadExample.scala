package w1

import java.util.UUID

object HelloWorldThreadExample extends App {
  val t1 = new HelloWorld
  val t2 = new HelloWorld
  t1.start()
  t2.start()
  println("Started threads")
  t1.join()
  t2.join()
  println("Joined threads")
}

class HelloWorld extends Thread {
  override def run(): Unit = {
    val id = UUID.randomUUID()
    println(s"${id.toString} Hello")
    println(s"${id.toString} World")
  }
}
