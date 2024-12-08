package day6

private fun countUniqueGuardPositions(situationMap: List<String>): Int {
    val mutableSituationMap = situationMap.map { it.toCharArray() }.toTypedArray()
    val guard = findGuard(situationMap)
    var count = 0

    while (guard.isOnMap) {
        val (i, j) = guard.position
        if (mutableSituationMap[i][j] != 'X') {
            count++
            mutableSituationMap[i][j] = 'X'
        }
        guard.move(mutableSituationMap)
    }
    printGrid(mutableSituationMap)
    return count
}


fun main() {
    val input = readInput()
    println("Answer to day 6 problem 1: ${countUniqueGuardPositions(input)}")
}