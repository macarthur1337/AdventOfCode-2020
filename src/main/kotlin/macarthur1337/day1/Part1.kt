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

    val result = find2020(0, lineList)

    println("Found: $result")
}

/**
 * recursively find solution
 */
fun find2020(index: Int, list: MutableList<Int>) : Int {
    val candidate = list[index]
    println("trying $candidate")
    val candidates = list
    candidates[index] = 0 //remove index itself
    candidates.forEach { if(it + candidate == 2020){
        println("Found: $candidate + $it")
        return candidate * it
        }
    }
    val newCandidate = index + 1
    return if(newCandidate <= list.size -1){
        find2020(newCandidate, list)
    }
    else{
        println("No candidates found")
        0
    }
}
