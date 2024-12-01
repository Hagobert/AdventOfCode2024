package com.aoc24.day1

fun similarityScore(left: List<Int>, right: List<Int>): Int {
    var similarityScore = 0
    val rightIdCounts = right.groupingBy { it }.eachCount()
    left.forEach {
        if (rightIdCounts.containsKey(it)) {
            similarityScore += it * rightIdCounts[it]!!
        }
    }
    return similarityScore
}

fun main() {
    val inputLists = readInput("src/main/kotlin/day1/inputProblem1.txt")
    println(similarityScore(inputLists[0], inputLists[1]))
}