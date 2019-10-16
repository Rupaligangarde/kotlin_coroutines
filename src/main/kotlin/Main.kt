import kotlinx.coroutines.*

fun main() {
    exampleLuanchCorountineWaiting()
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


fun exampleLuanchGlobal() = runBlocking{
    println("one = from ${Thread.currentThread().name}")

    GlobalScope.launch {
        printlnDelay("two = from ${Thread.currentThread().name}")
    }
    println("three = from ${Thread.currentThread().name}")

    delay(1000)
}

fun exampleLuanchGlobalWaiting() = runBlocking{
    println("one = from ${Thread.currentThread().name}")

    val job = GlobalScope.launch {
        printlnDelay("two = from ${Thread.currentThread().name}")
    }
    println("three = from ${Thread.currentThread().name}")

    job.join()
}


fun exampleLuanchCorountineWaiting() = runBlocking{
    println("one = from ${Thread.currentThread().name}")

    this.launch {
        printlnDelay("two = from ${Thread.currentThread().name}")
    }
    println("three = from ${Thread.currentThread().name}")
}

