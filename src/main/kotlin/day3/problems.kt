package day3

import java.io.File

private fun readInput(): String {
    return File("src/main/kotlin/day3/input.txt").readText()
}


private fun parseInput(input: String): List<Pair<Int, Int>> {
    val regex = "mul\\((\\d+),(\\d+)\\)|do\\(\\)|don't\\(\\)".toRegex()
    val matches = regex.findAll(input)
    return matches.map { matchResult ->
        val (num1, num2) = matchResult.destructured
        num1.toInt() to num2.toInt()
    }.toList()
}

private fun multiply(input: List<Pair<Int, Int>>): Int {
    var result = 0
    input.forEach {
        result += it.first * it.second
    }
    return result
}

fun main() {
    val input = readInput()
    println(multiply(parseInput(input)))
    println(parseInput(input).size)
}