package macarthur1337.day6

import org.springframework.util.ResourceUtils
import java.io.InputStream

/**
 * @author N. E. Absil
 * @since 06-12-2020
 */
fun main() {
    val inputStream: InputStream = ResourceUtils.getFile("classpath:input6").inputStream()
    val groups = mutableListOf<Int>()

    var line = IntArray(26)
    var count = 0
    inputStream.bufferedReader().useLines { lines ->
        lines.forEach {
            if (it.isNotEmpty()) {
                it.toCharArray().forEach { letter -> line[letter.toInt() - 97]++ }
                count++
            } else {
                groups.add(line.sumBy { if (it == count) 1 else 0 })
                count = 0
                line = IntArray(26)
            }
        }
    }
    groups.add(line.sumBy { if (it == count) 1 else 0 }) //add final group

    println("number of all groups with same answer: ${groups.sum()}")

}
