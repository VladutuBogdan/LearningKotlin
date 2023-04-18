import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
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

    // Handling Exceptions
//    runBlocking {
//        val asyncJob = GlobalScope.launch {
//            println("1. Exception created via launch coroutine")
//            // Will be printed to the console by
//            // Thread.defaultUncaughtExceptionHandler
//            throw IndexOutOfBoundsException()
//        }
//        asyncJob.join()
//        println("2. Joined failed job")
//        val deferred = GlobalScope.async {
//            println("3. Exception created via async coroutine")
//            // Nothing is printed, relying on user to call await
//            throw ArithmeticException()
//        }
//        try {
//            deferred.await()
//            println("4. Unreachable, this statement is never executed")
//        } catch (e: Exception) {
//            println("5. Caught ${e.javaClass.simpleName}")
//        }
//    }


//    runBlocking {
//        val job = GlobalScope.launch {
//            println("1. Exception created via launch coroutine")
//            // Will NOT be handled by
//            // Thread.defaultUncaughtExceptionHandler
//            // since it is being handled later by `invokeOnCompletion`
//            throw IndexOutOfBoundsException()
//        }
//        // Handle the exception thrown from `launch` coroutine builder
//        job.invokeOnCompletion { exception ->
//            println("2. Caught $exception")
//        }
//        // This suspends coroutine until this job is complete.
//        job.join()
//    }

    // custom exception
//    runBlocking {
//        // 1
//        val exceptionHandler = CoroutineExceptionHandler { _,
//                                                           exception ->
//            println("Caught $exception")
//        }
//        // 2
//        val job = GlobalScope.launch(exceptionHandler) {
//            throw AssertionError("My Custom Assertion Error!")
//        }
//        job.join()
//    }

//    runBlocking {
//        // Set this to ’true’ to call await on the deferred variable
//        val callAwaitOnDeferred = true
//        val deferred = GlobalScope.async {
//            // This statement will be printed with or without
//            // a call to await()
//            println("Throwing exception from async")
//            throw ArithmeticException("Something Crashed")
//            // Nothing is printed, relying on a call to await()
//        }
//        println("2")
//        if (callAwaitOnDeferred) {
//            try {
//                deferred.await()
//            } catch (e: ArithmeticException) {
//                println("Caught ArithmeticException")
//            }
//        }
//    }

//    runBlocking {
//        // Global Exception Handler
//        val handler = CoroutineExceptionHandler { _, exception ->
//            println("Caught $exception with suppressed " +
//                    // Get the suppressed exception
//                    "${exception.suppressed?.contentToString()}")
//        }
//        // Parent Job
//        val parentJob = GlobalScope.launch(handler) {
//            // Child Job 1
//            launch {
//                try {
//                    delay(Long.MAX_VALUE)
//                } catch (e: Exception) {
//                    println("${e.javaClass.simpleName} in Child Job 1")
//                } finally {
//                    throw ArithmeticException()
//                }
//            }
//            // Child Job 2
//            launch {
//                delay(100)
//                throw IllegalStateException()
//            }
//            // Delaying the parentJob
//            delay(Long.MAX_VALUE)
//        }
//        // Wait until parentJob completes
//        parentJob.join()
//    }

    // Cancel
//    runBlocking {
//        val job = launch {
//            repeat(1000) { i ->
//                println("$i. Crunching numbers [Beep.Boop.Beep]...")
//                delay(500L)
//            }
//        }
//        delay(1300L) // delay a bit
//        println("main: I am tired of waiting!")
//        job.cancel() // cancels the job
//        job.join() // waits for job’s completion
//        println("main: Now I can quit.")
//    }

//    runBlocking {
//        val parentJob = launch {
//            val childOne = launch {
//                repeat(1000) { i ->
//                    println("Child Coroutine 1: " +
//                            "$i. Crunching numbers [Beep.Boop.Beep]...")
//                    delay(500L)
//                }
//            }
//            // Handle the exception thrown from `launch`
//            // coroutine builder
//            childOne.invokeOnCompletion { exception ->
//                println("Child One: ${exception?.message}")
//            }
//            val childTwo = launch {
//                repeat(1000) { i ->
//                    println("Child Coroutine 2: " +
//                            "$i. Crunching numbers [Beep.Boop.Beep]...")
//                    delay(500L)
//                }
//            }
//            // Handle the exception thrown from `launch`
//            // coroutine builder
//            childTwo.invokeOnCompletion { exception ->
//                println("Child Two: ${exception?.message}")
//            }
//        }
//        delay(1200L)
//        println("Calling cancelChildren() on the parentJob")
//        parentJob.cancelChildren()
//        println("parentJob isActive: ${parentJob.isActive}")
//    }

    // Timeout
//    runBlocking {
//        withTimeout(1500L) {
//            repeat(1000) { i ->
//                println("$i. Crunching numbers [Beep.Boop.Beep]...")
//                delay(500L)
//            }
//        }
//    }

//    // 1
//    val list = listOf(1, 2, 3)
//    // 2
//    list.filter {
//        // 3
//        print("filter, ")
//        // 4
//        it > 0
//        // 5
//    }.map {
//        // 6
//        print("map, ")
//        // 7
//    }.forEach {
//        // 8
//        print("forEach, ")
//    }

    // asSequence
//    val list = listOf(1, 2, 3)
//    // 1
//    list.asSequence().filter {
//        print("filter, ")
//        it > 0
//    }.map {
//        print("map, ")
//    }.forEach {
//        print("forEach, ")
//    }


    // sequence
//    fun sequenceExample() = sequence {
//        yieldAll(generateSequence(2) { it * 2 })
//    }
//    val sequence = sequenceExample().take(10)
//    // 2
//    sequence.forEach {
//        print("$it ")
//    }


    // Channel
//    val fruitArray = arrayOf("Apple", "Banana", "Pear", "Grapes",
//        "Strawberry")
//    val kotlinChannel = Channel<String>(0)
//    runBlocking {
//        // 3
//        GlobalScope.launch {
//            for (fruit in fruitArray) {
//                // 4
//                kotlinChannel.send(fruit)
//                // 5
//                if (fruit == "Grapes") {
//                    // 6
//                    kotlinChannel.close()
//                }
//            }
//        }
//        // 7
//        for (fruit in kotlinChannel) {
//            println(fruit)
//        }
//        // 8
//        println("Done!")
//    }

    // send
//    val fruitArray = arrayOf("Apple", "Banana", "Pear", "Grapes",
//        "Strawberry")
//    val kotlinBufferedChannel = Channel<String>(2)
//    runBlocking {
//        launch {
//            for (fruit in fruitArray) {
//                kotlinBufferedChannel.send(fruit)
//                println("Produced: $fruit")
//            }
//            kotlinBufferedChannel.close()
//        }
//        launch {
//            for (fruit in kotlinBufferedChannel) {
//                println("Consumed: $fruit")
//                delay(1000)
//            }
//        }
//    }

    // offer
//    val fruitArray = arrayOf("Apple", "Banana", "Pear", "Grapes",
//        "Strawberry")
//    val kotlinChannel = Channel<String>()
//    runBlocking {
//        launch {
//            for (fruit in fruitArray) {
//                val wasSent = kotlinChannel.offer(fruit)
//                if (wasSent) {
//                    println("Sent: $fruit")
//                } else {
//                    println("$fruit wasn’t sent")
//                }
//            }
//            kotlinChannel.close()
//        }
//        for (fruit in kotlinChannel) {
//            println("Received: $fruit")
//        }
//        println("Done!")
//    }

    // many consumers, race condition
//    // 1
//    val fruitArray = arrayOf("Apple", "Banana", "Pear", "Grapes",
//        "Strawberry")
//    // 2
//    val kotlinChannel = Channel<String>()
//    // 3
//    runBlocking {
//        // 4 Producer
//        GlobalScope.launch {
//            // Send data in channel
//            kotlinChannel.send(fruitArray[0])
//        }
//        // 5 Consumers
//        GlobalScope.launch {
//            kotlinChannel.consumeEach { value ->
//                println("Consumer 1: $value")
//            }
//        }
//        GlobalScope.launch {
//            kotlinChannel.consumeEach { value ->
//                println("Consumer 2: $value")
//            }
//        }
//        // 6
//        println("Press a key to exit...")
//        readLine()
//        // 7
//        kotlinChannel.close()
//    }

}