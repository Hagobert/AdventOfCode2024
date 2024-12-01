package com.aoc24.day1

import java.io.File

fun readInput(filename: String): List<List<Int>> {
    return File(filename).readLines()
        .map { it.split(" ", limit = 2) }
        .map { (it[0].trim().toInt() to it[1].trim().toInt()) }
        .unzip().toList()
}