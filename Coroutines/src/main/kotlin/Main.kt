import kotlinx.coroutines.*

fun main() {

  // Starting 10.00 corroutines on a different threads
//  (1..10000).forEach {
//    GlobalScope.launch {
//      val threadName = Thread.currentThread().name
//      println("$it printed on thread ${threadName}")
//    }
//  }
//  Thread.sleep(1000)

  // Delay in a coroutine
//  GlobalScope.launch {
//    println("Hello coroutine!")
//    delay(500)
//    println("Right back at ya!")
//  }
//  Thread.sleep(1000)

  // Coroutine will start when is needed, on .join(); Will show: Ping, Pong, Ping
//  val job1 = GlobalScope.launch(start = CoroutineStart.LAZY) {
//    delay(200)
//    println("Pong")
//    delay(200)
//  }
//  GlobalScope.launch {
//    delay(200)
//    println("Ping")
//    job1.join()
//    println("Ping")
//    delay(200)
//  }
//  Thread.sleep(1000)
}