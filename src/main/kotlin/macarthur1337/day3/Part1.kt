package macarthur1337.day3

import org.springframework.util.ResourceUtils
import java.io.InputStream

/**
 * @author N. E. Absil
 * @since 30-11-2020
 */
fun main() {
    val inputStream: InputStream = ResourceUtils.getFile("classpath:input3").inputStream()
    val lineList = mutableListOf<String>()

    inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }

    var count = 0
    println("Trees: ${lineList.drop(1).count {
        count += 3
        it[count.rem(31)] == '#'
    }}")
}
