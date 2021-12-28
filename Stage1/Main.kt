package converter
// Project Number Base Converter
// Stage 1/4: Convert decimals

class numberBaseConverter() {
    var isOn = false
    var num =0
    var base = 2

    fun setup() {
        isOn = false
    }

    fun input() {
        println("Enter number in decimal system: ")
        num = readLine()!!.toInt()
        println("Enter target base: ")
        base = readLine()!!.toInt()

        convert()
    }


    fun convert() {
        var result = ""
        if (base == 16) {
            result = Integer.toHexString(num)
        } else if (base == 8) {
            result = Integer.toOctalString(num)
        } else if (base == 2) {
            result = Integer.toBinaryString(num)
        }

        println("Conversion result: $result")
    }


}

fun main() {
    val obj = numberBaseConverter()

    obj.setup()

    do {

        obj.input()

    } while (obj.isOn)

}