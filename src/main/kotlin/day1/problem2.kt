package day1

fun similarityScore(left: List<Int>, right: List<Int>): Int {
    val rightIdCounts = right.groupingBy { it }.eachCount()
    return left.sumOf { it * (rightIdCounts[it] ?: 0) }
}

fun main() {
    val inputLists = readInput("src/main/kotlin/day1/input.txt")
    println(similarityScore(inputLists[0], inputLists[1]))
}