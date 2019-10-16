import kotlinx.coroutines.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

fun main() {
    exampleAsyncAwait()
}


suspend fun printlnDelay(message: String) {
    //complex-calculations
    delay(1000)
    println(message)
}


fun exampleBlocking() = runBlocking() {
    println("one")
    printlnDelay("two")
    println("three")
}


//runs on diff thread but still blocks main thread
fun exampleBlockingDispatchers() {
    runBlocking(Dispatchers.Default) {
        println("one = from ${Thread.currentThread().name}")
        printlnDelay("two = from ${Thread.currentThread().name}")
    }

    println("three = from ${Thread.currentThread().name}")
    //this runs only after runBlocking is fully executed
}


fun exampleLuanchGlobal() = runBlocking {
    println("one = from ${Thread.currentThread().name}")

    GlobalScope.launch {
        printlnDelay("two = from ${Thread.currentThread().name}")
    }
    println("three = from ${Thread.currentThread().name}")

    delay(1000)
}

fun exampleLuanchGlobalWaiting() = runBlocking {
    println("one = from ${Thread.currentThread().name}")

    val job = GlobalScope.launch {
        printlnDelay("two = from ${Thread.currentThread().name}")
    }
    println("three = from ${Thread.currentThread().name}")

    job.join()
}


fun exampleLuanchCorountineWaiting() = runBlocking {
    println("one = from ${Thread.currentThread().name}")

    val customDispatcher = Executors.newFixedThreadPool(2).asCoroutineDispatcher()
    launch(customDispatcher) {
        printlnDelay("two = from ${Thread.currentThread().name}")
    }
    println("three = from ${Thread.currentThread().name}")
    (customDispatcher.executor as ExecutorService).shutdown()
}


suspend fun calculateHardThings(value: Int): Int {
    delay(1000)
    return value * value
}

fun exampleAsyncAwait() = runBlocking {

    val startTime = System.currentTimeMillis()
    val deferred1 = async { calculateHardThings(10) }
    val deferred2 = async { calculateHardThings(20) }
    val deferred3 = async { calculateHardThings(30) }

    val sum = deferred1.await() + deferred2.await() + deferred3.await()

    val endTime = System.currentTimeMillis()
    println("sum = $sum \ncalculated in ${endTime - startTime}")
}
