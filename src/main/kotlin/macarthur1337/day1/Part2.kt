package macarthur1337.day1

import org.springframework.util.ResourceUtils
import java.io.InputStream

/**
 * @author N. E. Absil
 * @since 30-11-2020
 */
fun main() {
    val inputStream: InputStream = ResourceUtils.getFile("classpath:input1").inputStream()
    val lineList = mutableListOf<Int>()

    inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it.toInt()) } }

    //brute force / random walk
    var unfound = true
    while(unfound){
        val first = lineList.shuffled()
        val second = lineList.shuffled()
        val third = lineList.shuffled()

        var index = 0
        while(index < lineList.size-1){
            if(first[index] + second[index] + third[index] == 2020){
                println("Found ${first[index]} + ${second[index]} + ${third[index]}")
                val result = first[index] * second[index] * third[index]
                println("Found: $result")
                unfound = false
                break
            }
            index++
        }
    }
}
