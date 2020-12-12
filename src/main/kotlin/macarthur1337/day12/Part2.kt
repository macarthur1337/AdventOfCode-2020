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
            'N', 'S', 'E', 'W' -> moveWaypoint(instruction, value)
            'F' -> travel(value)
            'L' -> changeHeading(value)
            'R' -> changeHeading(-value)
        }
    }
    println("$latitude $longitude")
    println("Manhattan distance ${latitude.absoluteValue + longitude.absoluteValue}")
}

data class Waypoint(var latitude: Int = 10, var longitude: Int = 1)
val waypoint = Waypoint()

fun moveWaypoint(instruction: Char, value: Int){
    when(instruction) {
        'N' -> waypoint.longitude += value
        'S' -> waypoint.longitude -= value
        'E' -> waypoint.latitude += value
        'W' -> waypoint.latitude -= value
    }
}

fun travel(value: Int){
    longitude += waypoint.longitude * value
    latitude += waypoint.latitude * value
}

fun changeHeading(value: Int){
    val current = Waypoint(latitude= waypoint.latitude, longitude = waypoint.longitude)
    when(value){
        180, -180 -> {
            waypoint.longitude *= -1
            waypoint.latitude *= -1
        }
        90, -270 -> {
            waypoint.longitude = current.latitude
            waypoint.latitude = -current.longitude
        }
        -90, 270 -> {
            waypoint.longitude = -current.latitude
            waypoint.latitude = current.longitude
        }
        else -> throw Exception("unknown heading: $value")
    }
}


