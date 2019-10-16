import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    exampleBlockingDispatchers()
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