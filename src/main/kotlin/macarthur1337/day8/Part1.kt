package macarthur1337.day8

import org.springframework.util.ResourceUtils
import java.io.InputStream

/**
 * @author N. E. Absil
 * @since 08-12-2020
 */
fun main() {
    val inputStream: InputStream = ResourceUtils.getFile("classpath:input8").inputStream()

    inputStream.bufferedReader().useLines { lines ->
        lines.forEach {
            instructions.add(Instruction(it.substringBefore(' '), it.substringAfter(' ').toInt()))
        }
    }

    execute(0)
    println(accumulator)
}

fun execute(line: Int): Boolean {
    if (line == instructions.size) return true //reached end of input
    val instruction = instructions[line]

    return if (instruction.read) {
        false
    } else {
        var next = line
        when (instruction.instruction) {
            "acc" -> {
                accumulator += instruction.value
                next++
            }
            "nop" -> next++
            "jmp" -> next += instruction.value
        }
        instructions[line].read = true
        execute(next)
    }
}

var accumulator = 0

var instructions = mutableListOf<Instruction>()

data class Instruction(var instruction: String, val value: Int, var read: Boolean = false)
