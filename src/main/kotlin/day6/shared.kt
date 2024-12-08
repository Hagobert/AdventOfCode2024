package day6

import shared.Vector2D
import shared.isVectorInGrid
import java.io.File

data class Guard(
    var position: Vector2D,
    var orientation: Vector2D,
    var isOnMap: Boolean = true
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Guard

        if (position != other.position) return false
        if (orientation != other.orientation) return false

        return true
    }

    override fun hashCode(): Int {
        var result = position.hashCode()
        result = 31 * result + orientation.hashCode()
        return result
    }

    fun move(situationMap: Array<CharArray>) {
        val nextPosition = position + orientation
        if (!isVectorInGrid(nextPosition, Pair(situationMap.size, situationMap[0].size))) {
            isOnMap = false
        } else {
            val (i, j) = nextPosition
            if (situationMap[i][j] == '#') {
                orientation = Vector2D(orientation.y, orientation.x * -1)
            } else {
                position = nextPosition
            }
        }
    }
}

internal fun findGuard(input: List<String>): Guard {
    val guardMap = mapOf(Pair('^', Vector2D(-1, 0)), Pair('>', Vector2D(0, 1)), Pair('v', Vector2D(1, 0)), Pair('<', Vector2D(0, -1)))
    input.forEachIndexed { i, row ->
        row.forEachIndexed { j, value ->
            if (guardMap.containsKey(value)) {
                return Guard(Vector2D(i, j), guardMap.getValue(value))
            }
        }
    }
    throw IllegalArgumentException("No Guard found")
}

internal fun readInput(): List<String> {
    return File("src/main/kotlin/day6/input.txt").readLines()
}

internal fun printGrid(grid: Array<CharArray>) {
    for (row in grid) {
        for (cell in row) {
            print(cell)
        }
        println()
    }
}