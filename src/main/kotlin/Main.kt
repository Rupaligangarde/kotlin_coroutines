fun main(){
    exampleBlocking()
}


fun printlnDelay(message: String){
    //complex-calculations
    Thread.sleep(1000)
    println(message)
}


fun exampleBlocking(){
    println("one")
    printlnDelay("two")
    println("three")
}