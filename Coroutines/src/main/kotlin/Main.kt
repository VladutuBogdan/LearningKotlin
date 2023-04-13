import kotlinx.coroutines.*
import kotlin.concurrent.thread

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

    // Job with children
//    with(GlobalScope) {
//        val parentJob = launch {
//            delay(200)
//            println("I’m the parent")
//            delay(200)
//        }
//        launch(context = parentJob) {
//            delay(200)
//            println("I’m a child")
//            delay(200)
//        }
//        if (parentJob.children.iterator().hasNext()) {
//            println("The Job has children ${parentJob.children}")
//        } else {
//            println("The Job has NO children")
//        }
//        Thread.sleep(1000)
//    }

    // delay and repeat
//    var isDoorOpen = false
//    println("Unlocking the door... please wait.\n")
//    GlobalScope.launch {
//        delay(3000)
//        isDoorOpen = true
//    }
//    GlobalScope.launch {
//        repeat(4) {
//            println("Trying to open the door...\n")
//            delay(800)
//            if (isDoorOpen) {
//                println("Opened the door!\n")
//            } else {
//                println("The door is still locked\n")
//            }
//        }
//    }
//    Thread.sleep(5000)

    // first one launch in the default thread and the second one in the main thread
//    GlobalScope.launch {
//        val bgThreadName = Thread.currentThread().name
//        println("I’m Job 1 in thread $bgThreadName")
//        delay(200)
//        GlobalScope.launch(Dispatchers.Main) {
//            val uiThreadName = Thread.currentThread().name
//            println("I’m Job 2 in thread $uiThreadName")
//        }
//
//    }
//    Thread.sleep(1000)

    // callback function
//    fun getUserFromNetworkCallback(
//        userId: String,
//        onUserReady: (String) -> Unit) {
//        thread {
//            Thread.sleep(1000)
//            println(userId)
//            onUserReady("user")
//        }
//        println("end")
//    }
//    getUserFromNetworkCallback("asda") { println(it) }

    // suspend function
//    suspend fun getUserSuspend(userId: String): String {
//        delay(1000)
//        return userId
//    }
//
//    GlobalScope.launch {
//        val user = getUserSuspend("101")
//        println(user)
//    }
//    println("forst")
//    runBlocking {
//        val user = getUserSuspend("42")
//        println(user)
//    }
//    println("second")
//
//    Thread.sleep(2500)


//    fun readFile(path: String, onReady: (String) -> Unit) {
//        Thread.sleep(1000)
//        // some heavy operation
//        onReady(path)
//    }
//
//
//    suspend fun readFileSuspend(path: String) {
//        GlobalScope.launch(Dispatchers.Default) {
//            readFile(path) { file ->
//                println(file);
//            }
//        }
//    }
//
//    runBlocking { readFileSuspend("asda"); }
//
//    Thread.sleep(2500)

    // async and await
//    suspend fun getUser(user: String): String {
//        delay(3000);
//
//        return user;
//    }
//
//    GlobalScope.launch {
//        val userData = async { getUser("userId")  }
//        val userData2 = async { getUser("userId2")  }
//        println(userData.await())
//        println(userData2.await())
//    }
//
//    Thread.sleep(5000)

//    suspend fun getUserByIdFromNetwork(user: String) =
//        GlobalScope.async {
//            println("Retrieving user from network")
//            delay(3000)
//            println("Still in the coroutine")
//            user
//        }
//
//    GlobalScope.launch {
//        val deferredUser = getUserByIdFromNetwork("Bogdan")
//        println(deferredUser.await())
//    }
//    Thread.sleep(5000)

//    suspend fun getUser(user: String): String {
//        delay(3000);
//
//        return user;
//    }
//
//    fun printEverything(st: String, st2: String) {
//        println("${st} ${st2}")
//    }
//    GlobalScope.launch {
//        val userData = async { getUser("userId")  }
//        val userData2 = async { getUser("userId2")  }
//        printEverything(userData.await(), userData2.await())
//    }
//
//    Thread.sleep(5000)

    // Join, cancel
//    var job = GlobalScope.launch {
//        Thread.sleep(3500);
//        println("This is some text");
//    }
//
//    runBlocking {
//        job.join();
//    }
//    println("This is some text 2");
//    Thread.sleep(5000)

//    var job = GlobalScope.launch {
//        Thread.sleep(3500);
//        if (isActive) {
//            println("This is some text");
//        }
//    }
//
//
//    Thread.sleep(1000)
//    job.cancel();
//    println("This is some text 2");
//    Thread.sleep(5000)
}