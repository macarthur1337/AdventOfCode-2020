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

    lineList.forEach { if(checkPositions(it)) passwords.add(it) }

    println("Found: ${passwords.size}")
}

/**
 * recursively find solution
 */
fun checkPositions(line: String) : Boolean {
    val firstPosition = line.substringBefore('-').toInt()-1
    val secondPosition = line.substringAfter('-').substringBefore(' ').toInt()-1
    val character = line.substringAfter(' ').substringBefore(':')[0]
    val password = line.substringAfterLast(' ')

    return (password[firstPosition] == character && password[secondPosition] != character)
            || (password[firstPosition] != character && password[secondPosition] == character)
}
