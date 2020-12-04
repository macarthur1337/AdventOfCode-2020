package macarthur1337.day4

import org.springframework.util.ResourceUtils
import java.io.InputStream

/**
 * @author N. E. Absil
 * @since 4-12-2020
 */
fun main() {
    val inputStream: InputStream = ResourceUtils.getFile("classpath:input4").inputStream()
    val passports = mutableListOf<Passport>()

    var line = ""
    inputStream.bufferedReader().useLines { lines ->
        lines.forEach {
            line = if (it.isNotBlank()) {
                if (line.isBlank()) it else "$line $it"
            } else {
                passports.add(Passport(line))
                ""
            }
        }
    }
    passports.add(Passport(line)) //add final passport

    println("valid passports day 1: ${passports.count { it.valid1() }}")
    println("valid passports day 2: ${passports.count { it.valid2() }}")

}


data class Passport(
    val entries: MutableMap<String, String> = mutableMapOf()
) {
    constructor(line: String) : this() {
        line.split(" ").toTypedArray().forEach {
            entries[it.substringBefore(':')] = it.substringAfter(':')
        }
    }

    fun valid1() = entries.size == 8 || (entries.size == 7 && !entries.contains("cid"))

    fun valid2(): Boolean {
        return valid1() &&
                entries["byr"]!!.toInt() in 1920..2002 &&
                entries["iyr"]!!.toInt() in 2010..2020 &&
                entries["eyr"]!!.toInt() in 2020..2030 &&
                ((entries["hgt"]!!.contains("cm") &&
                        entries["hgt"]!!.substringBefore("cm").toInt() in 150..193) ||
                        (entries["hgt"]!!.contains("in") &&
                                entries["hgt"]!!.substringBefore("in").toInt() in 59..76)) &&
                regexHCL.matches(entries["hcl"]!!) &&
                eyeColors.contains(entries["ecl"]) &&
                regexPID.matches(entries["pid"]!!)
    }
}

val regexHCL = Regex("#([a-f-0-9]{6})")
val eyeColors = arrayOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
val regexPID = Regex("([0-9]{9})")
