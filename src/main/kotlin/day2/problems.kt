package day2

import java.io.File


data class LevelReportResult(val isValid: Boolean, val candidatesToBeIgnored: Set<Int>)

fun readInput(filename: String): List<List<Int>> {
    return File(filename).readLines()
        .map { line -> line.split(" ").map { it.toInt() } }
}


fun analyzeLevelReports(levelReports: List<List<Int>>, problemDamperActive: Boolean): Int {
    return levelReports.count { levelReport ->
        val result = analyzeLevelReport(levelReport)
        result.isValid || (problemDamperActive && result.candidatesToBeIgnored.any { analyzeLevelReport(levelReport.filterIndexed { i, _ -> i != it }).isValid })
    }
}

fun analyzeLevelReport(levelReport: List<Int>): LevelReportResult {
    val initialDifference = levelReport[1] - levelReport[0]
    if (initialDifference !in -3..3 || initialDifference == 0) return LevelReportResult(false, setOf(0, 1))

    levelReport.drop(2).forEachIndexed { i, value ->
        val diff = value - levelReport[i + 1]
        if (diff !in -3..3 || diff == 0 || (diff > 0) != (initialDifference > 0)) {
            // Normally, only the previous index (i+1) has to be considered for the problem damper. Only if i==0, index 0 is relevant as well since it sets the initial slope
            return LevelReportResult(false, if (i == 0) setOf(0, 1, 2) else setOf(i + 1, i + 2))
        }
    }
    return LevelReportResult(true, emptySet())
}


fun main() {
    val inputLists = readInput("src/main/kotlin/day2/input.txt")
    println("Answer to problem 1: ${analyzeLevelReports(inputLists, false)}")
    println("Answer to problem 2: ${analyzeLevelReports(inputLists, true)}")
}
