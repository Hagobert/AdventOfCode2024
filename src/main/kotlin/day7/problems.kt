package day7

import java.io.File


private fun readInput(): Map<Long, List<Long>> {
    return File("src/main/kotlin/day7/input.txt").readLines().associate { line ->
        line.split(":").let { (key, value) ->
            key.toLong() to value.trim().split(" ").map { it.toLong() }
        }
    }
}

private fun countSolvableEquations(equations: Map<Long, List<Long>>, operators: List<Char>): Long {
    val knownOperators = mutableMapOf<Int, List<List<Char>>>()

    return equations.entries.sumOf { (result, numbers) ->
        val operatorSize = numbers.size - 1
        val operatorList = knownOperators.computeIfAbsent(operatorSize) { generateCombinations(operatorSize, operators) }

        operatorList.firstOrNull { applyOperators(numbers, it) == result }?.let { result } ?: 0L
    }
}

fun generateCombinations(n: Int, operators: List<Char>): List<List<Char>> {
    return sequence {
        if (n == 0) {
            yield(listOf())
        } else {
            for (operator in operators) {
                for (combination in generateCombinations(n - 1, operators)) {
                    yield(listOf(operator) + combination)
                }
            }
        }
    }.toList()
}

private fun applyOperators(numbers: List<Long>, operators: List<Char>): Long {
    return numbers.reduceIndexed { index, acc, value ->
        if (index == 0) acc
        else when (operators[index - 1]) {
            '+' -> acc + value
            '*' -> acc * value
            '|' -> (acc.toString() + value.toString()).toLong()
            else -> throw IllegalArgumentException("Invalid operator ${operators[index - 1]}")
        }
    }
}

fun main() {
    println("Answer to day 7 problem 1: ${countSolvableEquations(readInput(), listOf('+', '*'))}")
    println("Answer to day 7 problem 2: ${countSolvableEquations(readInput(), listOf('+', '*', '|'))}") // too low
}