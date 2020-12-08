package macarthur1337.day7

import org.springframework.util.ResourceUtils
import java.io.InputStream

/**
 * @author N. E. Absil
 * @since 07-12-2020
 */
fun main() {
    val inputStream: InputStream = ResourceUtils.getFile("classpath:input7").inputStream()

    inputStream.bufferedReader().useLines { lines -> lines.forEach { nodes.add(match(it)) } }

    println(bagCount("shiny gold"))
}

fun bagCount(bag: String): Int {
    val list = nodes.filter { it.color == bag }[0].list
    //stop condition a ba g with no rules
    return if(list.isEmpty()) return 0 else list.toList().sumBy { it. second + it.second * bagCount(it.first) }
}



