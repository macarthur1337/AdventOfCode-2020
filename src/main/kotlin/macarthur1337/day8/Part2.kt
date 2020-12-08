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

    val copy = instructions.map { it.copy() }

    var finger = 0
    var incomplete = true
    while (incomplete) {
        accumulator = 0

        instructions = copy.map { it.copy() }.toMutableList()
        val pointer = instructions.filter { it.instruction == "nop" || it.instruction == "jmp" }[finger]

        pointer.instruction = if (pointer.instruction == "jmp") "nop" else "jmp"

        if (execute(0)) incomplete = false else finger++
    }

    println(accumulator)
}

