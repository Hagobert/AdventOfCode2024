package day4

import java.io.File

internal fun readInput(): Array<CharArray> {
    return File("src/main/kotlin/day4/input.txt").readText().split("\n").filter { it.isNotEmpty() }.map { it.toCharArray() }.toTypedArray()
}