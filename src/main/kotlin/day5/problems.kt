package day5

import java.io.File


data class UnorderedPair<T : Comparable<T>>(val a: T, val b: T) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UnorderedPair<*>

        return (a == other.a && b == other.b) || (a == other.b && b == other.a)
    }

    override fun hashCode(): Int {
        return (minOf(a, b).hashCode() * 31 + maxOf(a, b).hashCode())
    }

    companion object {
        fun <T : Comparable<T>> fromPair(pair: Pair<T, T>): UnorderedPair<T> {
            return UnorderedPair(minOf(pair.first, pair.second), maxOf(pair.first, pair.second))
        }
    }
}

private fun findCorrectlyOrderedUpdates(rules: List<String>, updates: List<String>): Int {
    val ruleMap = parseRules(rules)
    var middleSum = 0
    updates.forEach { update ->
        update.split(",").let { updateValues ->
            if (checkUpdate(ruleMap, updateValues)) {
                middleSum += updateValues[updateValues.size / 2].toInt()
            }
        }
    }
    return middleSum
}

private fun correctFalslyOrderedUpdates(rules: List<String>, updates: List<String>): Int {
    val ruleMap = parseRules(rules)
    var middleSum = 0
    updates.forEach { update ->
        update.split(",").toMutableList().let { updateValues ->
            if (!checkUpdate(ruleMap, updateValues)) {
                correctUpdate(ruleMap, updateValues, 0)
                middleSum += updateValues[updateValues.size / 2].toInt()
            }
        }
    }
    return middleSum
}

private fun checkUpdate(rules: Map<UnorderedPair<Int>, Pair<Int, Int>>, update: List<String>): Boolean {
    if (update.size == 1) {
        return true
    }
    (1 until update.size).forEach { i ->
        val pair = update[0].toInt() to update[i].toInt()
        val unorderedPair = UnorderedPair.fromPair(pair)
        if (rules.containsKey(unorderedPair)) {
            if (rules[unorderedPair] != pair) {
                return false
            }
        }
    }
    return checkUpdate(rules, update.slice(1 until update.size))
}

private fun correctUpdate(rules: Map<UnorderedPair<Int>, Pair<Int, Int>>, update: MutableList<String>, iStart: Int) {
    if (iStart == update.size - 1) {
        return
    }
    (iStart until update.size).forEach { i ->
        val pair = update[iStart].toInt() to update[i].toInt()
        val unorderedPair = UnorderedPair.fromPair(pair)
        if (rules.containsKey(unorderedPair)) {
            if (rules[unorderedPair] != pair) {
                val value = update.removeAt(i)
                update.add(iStart, value)
                return correctUpdate(rules, update, iStart)
            }
        }
    }
    return correctUpdate(rules, update, iStart + 1)
}


private fun parseRules(rules: List<String>): Map<UnorderedPair<Int>, Pair<Int, Int>> {
    return rules.associate { it.split("|").let { (left, right) -> UnorderedPair(left.toInt(), right.toInt()) to Pair(left.toInt(), right.toInt()) } }
}


private fun readInput(): Pair<List<String>, List<String>> {
    val input = File("src/main/kotlin/day5/input.txt").readLines()
    val splitIndex = input.indexOf("")
    val rules = input.subList(0, splitIndex)
    val updates = input.subList(splitIndex + 1, input.size)
    return Pair(rules, updates)
}


fun main() {
    val input = readInput()
    println("Answer to day 5 problem 1: ${findCorrectlyOrderedUpdates(input.first, input.second)}")
    println("Answer to day 5 problem 2: ${correctFalslyOrderedUpdates(input.first, input.second)}")

}