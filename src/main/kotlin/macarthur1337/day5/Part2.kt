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

    val sortedSeats = passes.sorted()
    var previousSeat = sortedSeats.first()
    sortedSeats.drop(1).forEach {
        if (it - previousSeat == 2) println(previousSeat + 1)
        previousSeat = it
    }
}


