package macarthur1337.day10

import org.springframework.util.ResourceUtils
import java.io.InputStream

/**
 * @author N. E. Absil
 * @since 10-12-2020
 */
fun main() {
    val inputStream: InputStream = ResourceUtils.getFile("classpath:input10").inputStream()

    inputStream.bufferedReader().useLines { lines -> lines.forEach { adapters.add(it.toInt()) } }

    var current = 0
    adapters.sorted().forEach {
        jolts[it - 1 - current]++
        current = it
    }
    //add last difference
    jolts[2]++

    println("total jolts for 1 is ${jolts[0]} and 3 is ${jolts[2]}")
    println(jolts[0] * jolts[2])
}

val adapters = mutableListOf<Int>()
val jolts = IntArray(3)
