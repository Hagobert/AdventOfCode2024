package day8

import shared.Vector2D
import shared.isVectorInGrid
import java.io.File

private fun readInput(): List<String> {
    return File("src/main/kotlin/day8/input.txt").readLines()
}

private fun getCharCoordinates(input: List<String>): Map<Char, List<Vector2D>> {
    return input.flatMapIndexed { i, line ->
        line.mapIndexedNotNull { j, c ->
            if (c != '.') Vector2D(i, j) to c else null
        }
    }.groupBy { it.second }.mapValues { charListEntry -> charListEntry.value.map { it.first } }
}

private fun calculateNumberOfUniqueAntidotes(
    charCoordinates: Map<Char, List<Vector2D>>,
    size: Pair<Int, Int>,
    calculateAntidotes: (Pair<Vector2D, Vector2D>, Pair<Int, Int>) -> Set<Vector2D>
): Int {
    return charCoordinates.values.flatMap { points -> points.combinations().map { calculateAntidotes(it, size) } }.flatten().toSet().size
}

fun <T> List<T>.combinations(): Sequence<Pair<T, T>> {
    return sequence {
        for (i in 0 until size - 1) {
            for (j in i + 1 until size) {
                yield(Pair(this@combinations[i], this@combinations[j]))
            }
        }
    }
}

private fun calculateAntidotesWithoutResonance(points: Pair<Vector2D, Vector2D>, size: Pair<Int, Int>): Set<Vector2D> {
    val (p1, p2) = points
    val difference = p2 - p1
    return setOf(p1 - difference, p2 + difference).filter { isVectorInGrid(it, size) }.toSet()
}

private fun calculateAntidotesWithResonance(points: Pair<Vector2D, Vector2D>, size: Pair<Int, Int>): Set<Vector2D> {
    val (p1, p2) = points
    val difference = p2 - p1
    return generateSequence(p1) { it - difference }.takeWhile { isVectorInGrid(it, size) }.toSet() +
            generateSequence(p2) { it + difference }.takeWhile { isVectorInGrid(it, size) }.toSet()
}

fun main() {
    val input = readInput()
    val charCoordinates = getCharCoordinates(input)
    println("Answer to day 8 problem 1: ${calculateNumberOfUniqueAntidotes(charCoordinates, Pair(input.size, input[0].length), ::calculateAntidotesWithoutResonance)}")
    println("Answer to day 8 problem 2: ${calculateNumberOfUniqueAntidotes(charCoordinates, Pair(input.size, input[0].length), ::calculateAntidotesWithResonance)}")
}