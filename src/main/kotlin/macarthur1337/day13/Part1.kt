package macarthur1337.day13

import org.springframework.util.ResourceUtils
import java.io.InputStream

/**
 * @author N. E. Absil
 * @since 13-12-2020
 */
fun main() {
    val inputStream: InputStream = ResourceUtils.getFile("classpath:input13").inputStream()


    val lines = inputStream.bufferedReader().lines().toArray()
    val timestamp = lines.first().toString().toInt()
    val bus = lines.last().toString().splitToSequence(',').filterNot { it == "x" }
        // Use remainder to find each buses leaving time before the timestamp.
        // The bus id minus the remainder is then the earliest time the bus leaves after
        // the timestamp (and the difference)
        .map { it.toInt() }.toList().minBy { it - (timestamp % it) }!!

    val difference = bus - (timestamp % bus)
    println(bus * difference)
}
