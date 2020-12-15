package macarthur1337.day15

/**
 * @author N. E. Absil
 * @since 15-12-2020
 */
fun main() {
    input.dropLast(1).forEach {
        spoken[it] = turn
        turn++
    }
    var lastSpoken = input.last()

    (turn until 30000000).forEach {
        if(spoken.contains(lastSpoken) ){
            val lastTurn = spoken[lastSpoken]!!
            spoken[lastSpoken] = it
            lastSpoken = it - lastTurn
        }
        else{
            spoken[lastSpoken] = it
            lastSpoken = 0
        }
    }
    println(lastSpoken)
}


