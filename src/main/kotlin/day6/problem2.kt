package day6

import shared.isVectorInGrid

private fun countPossibleTrapLoops(situationMap: List<String>): Int {
    val impedimentPositions = mutableSetOf<Pair<Int, Int>>()
    val mutableSituationMap = situationMap.map { it.toCharArray() }.toTypedArray()
    val guard = findGuard(situationMap)
    val mapSize = Pair(situationMap.size, situationMap[0].length)

    while (guard.isOnMap) {
        val nextPosition = guard.position + guard.orientation
        if (!isVectorInGrid(nextPosition, mapSize)) {
            break
        }
        val (ii, jj) = nextPosition

        if (mutableSituationMap[ii][jj] == '.') {
            mutableSituationMap[ii][jj] = '#'
            if (checkForLoop(mutableSituationMap, guard.copy())) {
                impedimentPositions.add(Pair(ii, jj))
                mutableSituationMap[ii][jj] = 'O'
            } else {
                mutableSituationMap[ii][jj] = 'X'
            }
        }
        guard.move(mutableSituationMap)
    }
    printGrid(mutableSituationMap)
    return impedimentPositions.size
}

private fun checkForLoop(situationMap: Array<CharArray>, guard: Guard): Boolean {
    val guardHistory = mutableListOf<Guard>()

    while (guard.isOnMap) {
        if (guardHistory.contains(guard)) {
            return true
        }
        guardHistory.add(guard.copy())
        guard.move(situationMap)
    }
    return false
}


fun main() {
    val input = readInput()
    println("Answer to day 6 problem 2: ${countPossibleTrapLoops(input)}")
}