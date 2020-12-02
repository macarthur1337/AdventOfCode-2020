package macarthur1337.day2

import org.springframework.util.ResourceUtils
import java.io.InputStream

/**
 * @author N. E. Absil
 * @since 30-11-2020
 */
fun main() {
    val inputStream: InputStream = ResourceUtils.getFile("classpath:input2").inputStream()
    val lineList = mutableListOf<String>()

    inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
    val passwords = mutableListOf<String>()

    lineList.forEach { if(checkValidity(it)) passwords.add(it) }

    println("Found: ${passwords.size}")
}

/**
 * recursively find solution
 */
fun checkValidity(line: String) : Boolean {
    val lowerBound = line.substringBefore('-').toInt()
    val upperBound = line.substringAfter('-').substringBefore(' ').toInt()
    val character = line.substringAfter(' ').substringBefore(':')[0]
    val password = line.substringAfterLast(' ')

    return password.count{c -> c == character} in lowerBound..upperBound
}
