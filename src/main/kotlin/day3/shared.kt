package day3

import java.io.File

internal fun readInput(): String {
    return File("src/main/kotlin/day3/input.txt").readText()
}

internal fun sumOfProducts(input: List<Pair<Int, Int>>): Int {
    return input.sumOf { it.first * it.second }
}