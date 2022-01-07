package converter
// Project Number Base Converter
// Stage 4/4: Convert decimals

import java.math.BigDecimal
import java.math.RoundingMode

enum class State {MENU, MENU2, EXIT, BACK}

class NumberBaseConverter {
    //var isOn = false
    var num = ""
    var baseSrc = 2
    var targetSrc = 10

    var state: State = State.MENU

    fun setup() {
        //isOn = true
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

    fun doConversion() : State{
        println("Enter number in base $baseSrc to convert to base $targetSrc (To go back type /back)")
        num = readLine()!!.toString()

        if (num != "/back" && num != "bb") {
            val hasFractional = num.contains(".")

            var x = ""
            var frac = ""
            if (hasFractional){
                frac = num.substringAfter(".")
                val numWhole= num.substringBefore(".")

                val n = getDecimal(numWhole)
                x = convertFromDecimal(n)

                val xx = convertFractionalToDecimal(frac)
                x += ".${convertFractionalFromDecimal(xx)}"

            } else {
                val n = getDecimal(num)
                x = convertFromDecimal(n)
            }

            println("Conversion result: $x")

            return State.MENU2
        }

        return State.BACK
    }

    fun convertFromDecimal(n: String): String {
        return n.toBigInteger(10).toString(targetSrc)
    }

    fun getDecimal(n : String): String{
        return n.toBigInteger(baseSrc).toString(10)
    }

    fun remHexToDecimal(c: Char) = if (c in '0'..'9') Character.getNumericValue(c) else c.toUpperCase() - 'A' + 10

    fun remToHex(rem: Int): Char {
        var res = "$rem".first()
        if (targetSrc in 10..36 && rem in 10..36) {
            res = 'A' + (rem - 10)
        }
        return res
    }

    fun convertFractionalToDecimal(f: String): BigDecimal {
        if (baseSrc == 10) return BigDecimal("0.$f")
        if (f == "0") return BigDecimal.ZERO
        var sum = BigDecimal.ZERO
        val bdSourceBase = BigDecimal(baseSrc)
        for (i in f.indices) {
            val a = remHexToDecimal(f[i].toUpperCase()).toBigDecimal().setScale(5, RoundingMode.HALF_UP)
            sum += a / bdSourceBase.pow(i+1)
        }
        return sum
    }

    fun convertFractionalFromDecimal(f: BigDecimal): String {
        if (f == BigDecimal.ZERO) return "00000"
        if (targetSrc == 10) return "${f.setScale(5, RoundingMode.HALF_UP)}".replace("0.", "")
        var res = ""
        var frac = f
        val bdTargetBase = BigDecimal(targetSrc)
        for (i in 1..5) {
            val x = frac * bdTargetBase
            val d = x.toInt()
            val r = x - d.toBigDecimal()
            res += "${remToHex(d)}"
            frac = r
            if (r == BigDecimal.ZERO) break
        }
        return res
    }

    fun exitSystem(): State{
        //isOn = true

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
// 143