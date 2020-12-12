package macarthur1337.day12

import org.springframework.util.ResourceUtils
import java.io.InputStream
import kotlin.math.absoluteValue

/**
 * @author N. E. Absil
 * @since 12-12-2020
 */
fun main() {
    val inputStream: InputStream = ResourceUtils.getFile("classpath:input12").inputStream()

    inputStream.bufferedReader().useLines { lines -> lines.forEach { instructions.add(it) } }

    instructions.forEach {
        val (first, second) = regex.matchEntire(it)!!.destructured
        val instruction = first.first()
        val value = second.toInt()
        when(instruction){
            'N', 'S', 'E', 'W' -> translate(instruction, value)
            'F' -> move(value)
            'L', 'R' -> rotation(instruction, value)
        }
    }
    println("$latitude $longitude")
    println("Manhattan distance: ${latitude.absoluteValue + longitude.absoluteValue}")

}

fun translate(instruction: Char, value: Int){
    when(instruction) {
        'N' -> longitude += value
        'S' -> longitude -= value
        'E' -> latitude += value
        'W' -> latitude -= value
    }
}

fun move(value: Int){
    when(heading){
        0 -> longitude += value
        90 -> latitude += value
        180 -> longitude -= value
        270 -> latitude -= value
        else -> throw Exception("unknown heading: $heading")
    }
}

fun rotation(instruction: Char, value: Int){
    val rotation = when(instruction){
        'R' -> value
        'L' -> -value
        else -> throw Exception("unknown rotation: $instruction")
    }
    heading = Math.floorMod(heading + rotation, 360)
}

val instructions = mutableListOf<String>()
var heading = 90
var longitude = 0
var latitude = 0
val regex = Regex(" ?(?<instruction>[\\w])(?<value>\\d+)")
