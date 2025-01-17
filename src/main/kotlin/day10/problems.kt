package day10

import java.io.File

fun mapHikingTrails(topography: List<List<Int>>, useRate: Boolean): Int {
    var numberOfTrails = 0

    topography.forEachIndexed { x, row ->
        row.forEachIndexed { y, c ->
            if (c == 0) numberOfTrails += findTrails(topography, x to y, mutableSetOf(), useRate)
        }
    }

    return numberOfTrails
}

fun findTrails(topography: List<List<Int>>, position: Pair<Int, Int>, peaks: MutableSet<Pair<Int, Int>>, useRate: Boolean): Int {
    val height = topography[position.first][position.second]
    if (height == 9) {
        if (peaks.add(position) || useRate) {
            return 1
        }
        return 0
    }
    var numberOfTrails = 0
    val sizeX = topography.size
    val sizeY = topography[0].size

    // Up
    if (position.first > 0 && topography[position.first - 1][position.second] == height + 1) {
        numberOfTrails += findTrails(topography, position.first - 1 to position.second, peaks, useRate)
    }

    // Right
    if (position.second < sizeY - 1 && topography[position.first][position.second + 1] == height + 1) {
        numberOfTrails += findTrails(topography, position.first to position.second + 1, peaks, useRate)
    }

    // Down
    if (position.first < sizeX - 1 && topography[position.first + 1][position.second] == height + 1) {
        numberOfTrails += findTrails(topography, position.first + 1 to position.second, peaks, useRate)
    }

    // Left
    if (position.second > 0 && topography[position.first][position.second - 1] == height + 1) {
        numberOfTrails += findTrails(topography, position.first to position.second - 1, peaks, useRate)
    }

    return numberOfTrails
}

fun readInput(): List<List<Int>> {
    return File("src/main/kotlin/day10/input.txt").readLines().map { line -> line.map { it.digitToInt() } }
}


fun main() {
    val input = readInput()
    println("Answer to day 10 problem 1: ${mapHikingTrails(input, false)}")
    println("Answer to day 10 problem 2: ${mapHikingTrails(input, true)}")
}