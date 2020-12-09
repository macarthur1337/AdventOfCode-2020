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

    inputStream.bufferedReader().useLines { lines -> lines.forEach { numbers.add(it.toBigInteger()) } }

    val preamble = 25
    var index = preamble

    while (permutations(numbers.subList(index - preamble, index)).filter { (a, b) -> a + b == numbers[index] }.any()) {
        index++
    }
    println(numbers[index])

    val result = contiguousSet(index)
    result.sort()

    println("solution: ${result.first() + result.last()}")
}

fun contiguousSet(end: Int): MutableList<BigInteger> {
    for (i in 0..end) {
        var sum: BigInteger = BigInteger.ZERO
        for (j in i..end) {
            sum += numbers[j]
            if (sum == numbers[end]) {
                return numbers.subList(i, j)
            }
            if (sum > numbers[end])
                break
        }
    }
    throw Exception("No set found")
}

