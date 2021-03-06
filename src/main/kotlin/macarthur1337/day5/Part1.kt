package macarthur1337.day5

import org.springframework.util.ResourceUtils
import java.io.InputStream

/**
 * @author N. E. Absil
 * @since 05-12-2020
 */
fun main() {
    val inputStream: InputStream = ResourceUtils.getFile("classpath:input5").inputStream()

    val passes = mutableListOf<Int>()

    inputStream.bufferedReader().useLines { lines ->
        lines.forEach {
            passes.add(
                seats(it.substring(0, 7), 1, 128) *
                        8 + seats(it.substring(7), 1, 8)
            )
        }
    }

    println(passes.max())
}

fun seats(pass: String, lower: Int, upper: Int): Int {
    if (pass.isNotEmpty()) {
        val newPass = pass.substring(1)
        val newRange = (lower + upper) / 2
        when (pass[0]) {
            'R', 'B' -> return seats(newPass, newRange, upper)
            'L', 'F' -> return seats(newPass, lower, newRange)
        }
    }
    return upper - 1
}
