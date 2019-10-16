import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    exampleBlocking()
}


suspend fun printlnDelay(message: String) {
    //complex-calculations
    delay(1000)
    println(message)
}


fun exampleBlocking() = runBlocking {
    println("one")
    printlnDelay("two")
    println("three")
}