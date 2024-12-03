package day3


private fun parseInput(input: String): List<Pair<Int, Int>> {
    val regex = "mul\\((\\d+),(\\d+)\\)|do\\(\\)|don't\\(\\)".toRegex()
    val results = mutableListOf<Pair<Int, Int>>()
    var enabled = true

    regex.findAll(input).forEach {
        when (it.value) {
            "do()" -> enabled = true
            "don't()" -> enabled = false
            else -> {
                if (enabled) {
                    val (num1, num2) = it.destructured
                    results.add(Pair(num1.toInt(), num2.toInt()))
                }
            }
        }
    }
    return results
}


fun main() {
    val input = readInput()
    println("Answer to day 3 problem 2: ${sumOfProducts(parseInput(input))}")
}