package day4


private fun findXmas(input: Array<CharArray>): Int {
    val rightCenters = mutableSetOf<Pair<Int, Int>>()
    val leftCenters = mutableSetOf<Pair<Int, Int>>()

    for (i in 0 until input.size - 2) {
        input[i].forEachIndexed { j, char ->
            if (char == 'M' || char == 'S') {
                rightCenters.addAll(searchDiagonal(i, j, input, true))
                leftCenters.addAll(searchDiagonal(i, j, input, false))
            }
        }
    }

    return rightCenters.intersect(leftCenters).size
}

private fun searchDiagonal(i: Int, j: Int, input: Array<CharArray>, djRight: Boolean): Set<Pair<Int, Int>> {
    if (((j >= input[0].size - 2) && djRight) || ((j < 2) && !djRight)) {
        return emptySet()
    }
    val dj = if (djRight) 1 else -1

    if ("" + input[i][j] + input[i + 1][j + 1 * dj] + input[i + 2][j + 2 * dj] in setOf("MAS", "SAM")) {
        return setOf(Pair(i + 1, j + 1 * dj))
    }
    return emptySet()
}

fun main() {
    val input = readInput()
    println("Answer to day 4 problem 2: ${findXmas(input)}")
}