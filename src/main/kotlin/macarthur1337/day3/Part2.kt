package macarthur1337.day3

import org.springframework.util.ResourceUtils
import java.io.InputStream
import java.math.BigInteger

/**
 * @author N. E. Absil
 * @since 30-11-2020
 */
fun main() {
    val inputStream: InputStream = ResourceUtils.getFile("classpath:input3").inputStream()
    val lineList = mutableListOf<String>()

    inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }

    val trees : BigInteger =
            countTrees(lineList, 1,1) *
            countTrees(lineList, 3,1) *
            countTrees(lineList, 5,1) *
            countTrees(lineList, 7,1) *
            countTrees(lineList, 1,2)
    println("Trees: $trees")
}

fun countTrees(list: MutableList<String>, slope: Int, down: Int) : BigInteger {
    var count = 0
    var line = down - 1

    return list.drop(down).count {
        line++
        if(line.rem(down) == 0) {
            count += slope
            it[count.rem(31)] == '#'
        } else false
    }.toBigInteger()
}
