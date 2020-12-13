package macarthur1337.day13

import org.springframework.util.ResourceUtils
import java.io.InputStream

/**
 * @author N. E. Absil
 * @since 13-12-2020
 */
fun main() {
    val inputStream: InputStream = ResourceUtils.getFile("classpath:input14").inputStream()


    val lines = inputStream.bufferedReader().lines().toArray()
    val buses = lines.last().toString().splitToSequence(',')
    val required = mutableListOf<Int>()
    var count = 1
    buses.forEach {
        when(it){
            "x" -> {}
            else -> required.add(count)
        }
        count++
    }
    println(required)

    var counter:Long = 0//100000000000000
    val test = buses.filterNot { it == "x" }.map { it.toInt() }.toList()


    while(true){
        counter++
        val schedule = mutableListOf<Int>()
        test.forEach { schedule.add((it - (counter % it)).toInt()) }
        if(schedule == required){
            println(counter+1)
            println(schedule)
            break

        }
    }


}
