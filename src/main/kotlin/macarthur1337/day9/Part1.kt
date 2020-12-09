package macarthur1337.day9

import org.springframework.util.ResourceUtils
import java.io.InputStream
import java.math.BigInteger

/**
 * @author N. E. Absil
 * @since 09-12-2020
 */
fun main() {
    val inputStream: InputStream = ResourceUtils.getFile("classpath:input9").inputStream()

    inputStream.bufferedReader().useLines { lines -> lines.forEach { numbers.add(it.toBigInteger()) }}

    val preamble = 25
    var index = preamble

    while (permutations(numbers.subList(index - preamble, index)).
            filter { (a, b) -> a + b == numbers[index] }.any()) { index++ }

    println(numbers[index])
}

val numbers = mutableListOf<BigInteger>()

fun permutations(list: MutableList<BigInteger>): Sequence<Pair<BigInteger, BigInteger>> = sequence {
    (0 until list.size).forEach { i ->
        (i + 1 until list.size).forEach { j -> yield(list[i] to list[j]) }
    }
}
