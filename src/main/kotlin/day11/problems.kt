package day11

import java.io.File
import java.math.BigInteger


private fun readInput(): List<String> {
    return File("src/main/kotlin/day11/input.txt").readText().split(" ")
}

private fun blink(stones: List<String>, nBlinks: Int): BigInteger {
    var previousStones = stones.groupingBy { it }.eachCount().mapValues { it.value.toBigInteger() }

    for (blink in 1..nBlinks) {
        val currentStones = mutableMapOf<String, BigInteger>()
        for ((stone, count) in previousStones) {
            if (stone.length % 2 == 0) {
                val half = stone.length / 2
                val left = stone.substring(0, half)
                val right = stone.substring(half).toBigInteger().toString()
                currentStones[left] = (currentStones[left] ?: BigInteger.ZERO) + count
                currentStones[right] = (currentStones[right] ?: BigInteger.ZERO) + count
            } else if (stone.toBigInteger() == BigInteger.ZERO) {
                currentStones["1"] = (currentStones["1"] ?: BigInteger.ZERO) + count
            } else {
                val newStone = (stone.toBigInteger() * BigInteger.valueOf(2024)).toString()
                currentStones[newStone] = (currentStones[newStone] ?: BigInteger.ZERO) + count
            }
        }
        previousStones = currentStones
    }

    return previousStones.values.sumOf { it }
}


fun main() {
    val input = readInput()
    println("Answer to day 11 problem 1: ${blink(input, 25)}")
    println("Answer to day 11 problem 2: ${blink(input, 75)}")
}