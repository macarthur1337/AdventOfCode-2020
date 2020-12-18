package macarthur1337.day16

import org.springframework.util.ResourceUtils
import java.io.InputStream

/**
 * @author N. E. Absil
 * @since 16-12-2020
 */
fun main() {
    val inputStream: InputStream = ResourceUtils.getFile("classpath:input16").inputStream()


    val lines = inputStream.bufferedReader().lines().toArray()
    lines.copyOfRange(0, lines.indexOfFirst { it == "" }).forEach {

        val (name, firstL, secondL, firstR, secondR) = rule.matchEntire(it.toString())!!.destructured
        rules.add(
            Rule(
                name,
                (firstL.toInt()..secondL.toInt()).toList().toIntArray(),
                (firstR.toInt()..secondR.toInt()).toList().toIntArray()
            )
        )
    }

    lines.drop(lines.indexOfFirst { it == "nearby tickets:" } + 1)
        .forEach { line -> tickets.add(line.toString().split(',').map { it.toInt() }.toIntArray()) }

    tickets.forEach { entries -> entries.forEach { entry -> if (!notInRange(entry)) errors.add(entry) } }
    println(errors.sum())
}

data class Rule(val rule: String, val first: IntArray, val second: IntArray) {
    fun inRange(number: Int) = number in first || number in second
}

fun notInRange(entry: Int): Boolean {
    rules.forEach { if (it.inRange(entry)) return true }
    return false
}

val rule = Regex(" ?(?<name>[\\s\\S]*): (?<firstL>\\d+)-(?<secondL>\\d+) or (?<firstR>\\d+)-(?<secondR>\\d+)")
val rules = mutableListOf<Rule>()
val tickets = mutableListOf<IntArray>()
val errors = mutableListOf<Int>()
