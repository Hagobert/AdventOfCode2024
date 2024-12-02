package day1

import kotlin.math.abs


private fun totalIdDistances(left: List<Int>, right: List<Int>): Int {
    return left.sorted().zip(right.sorted()).sumOf { (left, right) -> abs(right - left) }
}


fun main() {
    val inputLists = readInput("src/main/kotlin/day1/input.txt")
    println("Answer to day 1 problem 1: ${totalIdDistances(inputLists[0], inputLists[1])}")
}