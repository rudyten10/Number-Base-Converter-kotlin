package converter
// Project Number Base Converter
// Stage 3/4: Convert decimals
//import java.math.BigInteger

enum class State {MENU, MENU2, EXIT, FROM, TO, BACK}

class NumberBaseConverter {
    var isOn = false
    var num = ""
    var numDecimal = ""
    var baseSrc = 2
    var targetSrc = 10

    var state: State = State.MENU

    fun setup() {
        isOn = true
    }

    fun runSystem(){
        when (state){
            State.MENU -> state = menu()
            State.MENU2 -> state = doConversion()
            State.BACK -> state = menu()
            State.EXIT -> state = exitSystem()
            else -> throw UnsupportedOperationException("Unexpected state")
        }
    }

    fun menu(): State {
        println()
        println("0. Exit")
        println("Enter two numbers in format: {source base} {target base} (To quit type /exit)")
        val command = readLine()!!
        if (command == "/exit" || command == "e") {
            return State.EXIT
        } else {
            val (aa, bb) = command.split(" ")
            baseSrc = aa.toInt()
            targetSrc = bb.toInt()
        }

        return State.MENU2
    }
    fun menu2(): State {
        println()
        //println("1. /from")
        //println("2. /to")
        println("3. /back")
        println("0. Exit")

        doConversion()

        return State.MENU2
    }

    fun doConversion() : State{
        println("Enter number in base $baseSrc to convert to base $targetSrc (To go back type /back)")
        num = readLine()!!.toString()

        if (num != "/back") {
            getDecimal()

            convertFromDecimal()

            return State.MENU2
        }

        return State.BACK
    }

    fun convertFromDecimal() {
        val result = numDecimal.toBigInteger(10).toString(targetSrc)

        println("Conversion result: $result")
    }

    fun getDecimal(){
        numDecimal = num.toBigInteger(baseSrc).toString(10)
    }

    fun exitSystem(): State{
        isOn = true

        return State.MENU
    }
}

fun main() {
    //val obj = numberBaseConverter()
    NumberBaseConverter().run {

        do  runSystem() while ( state != State.EXIT)
    }

    //obj.setup()

    //do {

    //    obj.inputRunSystem()

    //} while (obj.isOn)

}
// 252 122 105