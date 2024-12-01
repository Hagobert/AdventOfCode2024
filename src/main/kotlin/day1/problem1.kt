package day1

import kotlin.math.abs


fun totalIdDistances(left: List<Int>, right: List<Int>): Int {
    return left.sorted().zip(right.sorted()).sumOf { (left, right) -> abs(right - left) }
}


fun main() {
    val inputLists = readInput("src/main/kotlin/day1/input.txt")
    println(totalIdDistances(inputLists[0], inputLists[1]))
}