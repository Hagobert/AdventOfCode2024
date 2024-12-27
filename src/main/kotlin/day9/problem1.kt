package day9

import java.io.File
import java.math.BigInteger


private fun compact(input: Pair<String, String>): BigInteger {
    var checkSum = BigInteger("0")
    var i = 0
    var j = input.first.length - 1
    var k = 0
    val forward = input.first
    val freeBlocks = input.second.iterator()
    var result = ""
    var nRemaining = forward[j].digitToInt()
    while (i < j) {
        val n = forward[i].digitToInt()
        result += i.toString().repeat(n)
        checkSum += (k..<k + n).sumOf { it.toBigInteger() * i.toBigInteger() }
        k += n
        i++

        var nFree = freeBlocks.next().digitToInt()
        while (nFree > nRemaining && j > i) {
            result += j.toString().repeat(nRemaining)
            checkSum += (k..<k + nRemaining).sumOf { it.toBigInteger() * j.toBigInteger() }
            k += nRemaining
            nFree -= nRemaining
            j--
            nRemaining = forward[j].digitToInt()
        }
        if (nFree > 0 && j > i) {
            result += j.toString().repeat(nFree)
            checkSum += (k..<k + nFree).sumOf { it.toBigInteger() * j.toBigInteger() }
            k += nFree
            nRemaining -= nFree
        }
    }
    if (nRemaining > 0) {
        result += j.toString().repeat(nRemaining)
        checkSum += (k..<k + nRemaining).sumOf { it.toBigInteger() * j.toBigInteger() }
    }
    return checkSum
}

private fun readInput(): Pair<String, String> {
    val input = File("src/main/kotlin/day9/input.txt").readText()
    val files = input.filterIndexed { index, _ -> index % 2 == 0 }
    val freeBlocks = input.filterIndexed { index, _ -> index % 2 != 0 }
    return files to freeBlocks
}

fun main() {
    val input = readInput()
    val result = compact(input)
    println("Answer to day 9 problem 1: ${result}")
}