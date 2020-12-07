package macarthur1337.day7

import org.springframework.util.ResourceUtils
import java.io.InputStream

/**
 * @author N. E. Absil
 * @since 07-12-2020
 */
fun main() {
    val inputStream: InputStream = ResourceUtils.getFile("classpath:input7").inputStream()

    inputStream.bufferedReader().useLines { lines -> lines.forEach { nodes.add(match(it)) } }
    countBags("shiny gold")
    println(nodes)
    println(seen.distinct().size)
}

val nodes = mutableListOf<Node>()
val seen = mutableListOf<String>()

data class Node(val color: String, val list: MutableMap<String, Int>)

fun match(rule: String): Node {
    val node = Node(rule.substringBefore(" bag"), mutableMapOf())
    if (!rule.contains("contain no other bags.")) {

        //at least one rule
        var newRule = rule.substringAfter("contain ")
        node.list[newRule.substringAfter(' ').substringBefore(" bag")] = newRule.substringBefore(' ').toInt()
        newRule = newRule.substringAfter(", ")
        while (newRule.contains(',')) {

            node.list[newRule.substringAfter(' ').substringBefore(" bag")] = newRule.substringBefore(' ').toInt()
            newRule = newRule.substringAfter(", ")
        }
        if (newRule[0].isDigit()) {
            node.list[newRule.substringAfter(' ').substringBefore(" bag")] =
                newRule.substringBefore(' ').toInt()
        }
    }
    return node
}

fun countBags(bag: String) {
    nodes.filter { it.list.contains(bag) }.forEach {
        seen.add(it.color)
        countBags(it.color)
    }
}
