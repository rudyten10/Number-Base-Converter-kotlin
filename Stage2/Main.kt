package converter
// Project Number Base Converter
// Stage 2/4: Convert to decimal

class numberBaseConverter() {
    var isOn = false
    var command = ""
    var num = ""
    var base = 2

    fun setup() {
        isOn = true
    }

    fun input() {
        println("Do you want to convert /from decimal or /to decimal? (To quit type /exit)")
        command = readLine()!!.toString()

        if (command == "/from" || command == "f") {
            println("Enter a number in decimal system: ")
            num = readLine()!!
            println("Enter the target base: ")
            base = readLine()!!.toInt()

            convertFromDecimal()
        } else if (command == "/to" || command == "t") {
            println("Enter source number: ")
            num = readLine()!!
            println("Enter source base: ")
            base = readLine()!!.toInt()

            convertToDecimal()
        } else if (command == "/exit") {
            isOn = false
        } else {
            isOn = true
        }
    }

    fun convertFromDecimal() {
        var result = ""
        if (base == 16) {
            result = Integer.toHexString(num.toInt())
        } else if (base == 8) {
            result = Integer.toOctalString(num.toInt())
        } else if (base == 2) {
            result = Integer.toBinaryString(num.toInt())
        }

        println("Conversion result: $result")
    }

    fun convertToDecimal() {
        var result = 0
        if (base == 16) {
            result = num.toString().toInt(16)
        } else if (base == 8) {
            result = num.toString().toInt(8)
        } else if (base == 2) {
            result = num.toString().toInt(2)
        }

        println("Conversion to decimal result: $result")
    }
}

fun main() {
    val obj = numberBaseConverter()
    obj.setup()

    do {
        obj.input()
    } while (obj.isOn)
}
// 76