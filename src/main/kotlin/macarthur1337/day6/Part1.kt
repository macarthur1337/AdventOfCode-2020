package macarthur1337.day6

import org.springframework.util.ResourceUtils
import java.io.InputStream

/**
 * @author N. E. Absil
 * @since 06-12-2020
 */
fun main() {
    val inputStream: InputStream = ResourceUtils.getFile("classpath:input6").inputStream()
    val groups = mutableListOf<CharArray>()

    var line = charArrayOf()
    inputStream.bufferedReader().useLines { lines ->
        lines.forEach {
            line = if (it.isNotEmpty()) {
                when {
                    line.isEmpty() -> it.toCharArray()
                    else -> line.union(it.toCharArray().asIterable()).toCharArray()
                }
            } else {
                groups.add(line)
                charArrayOf()
            }
        }
    }
    groups.add(line) //add final group

    println("number of groups: ${groups.sumBy { it.size }}")

}
