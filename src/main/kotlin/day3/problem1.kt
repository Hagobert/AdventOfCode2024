package day3

private fun parseInput(input: String): List<Pair<Int, Int>> {
    val regex = "mul\\((\\d+),(\\d+)\\)".toRegex()
    return regex.findAll(input).map { it.destructured.let { (num1, num2) -> num1.toInt() to num2.toInt() } }.toList()
}

fun main() {
    val input = readInput()
    println("Answer to day 3 problem 1: ${sumOfProducts(parseInput(input))}")
}