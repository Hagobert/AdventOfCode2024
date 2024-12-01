package day1

import com.aoc24.day1.readInput
import kotlin.math.abs


fun totalIdDistances(left: List<Int>, right: List<Int>): Int {
    return left.sorted().zip(right.sorted()).sumOf { (left, right) -> abs(right - left) }
}


fun main() {
    val inputLists = readInput("src/main/kotlin/day1/inputProblem1.txt")
    println(totalIdDistances(inputLists[0], inputLists[1]))
}