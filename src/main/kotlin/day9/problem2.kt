package day9

import java.io.File
import java.math.BigInteger


enum class Type {
    FREESPACE,
    FILE
}

interface FileSystemPart {
    val type: Type
    var size: Int
    var moved: Boolean
}

data class FreeSpace(override var size: Int) : FileSystemPart {
    override val type = Type.FREESPACE
    override var moved = false
}

data class FilePart(override var moved: Boolean, override var size: Int, val index: Int) : FileSystemPart {
    override val type = Type.FILE
}


private fun compact(input: List<FileSystemPart>): BigInteger {
    var i = input.size - 1
    val output = input.toMutableList()
    while (i >= 0) {
        if (output[i].type == Type.FILE && !output[i].moved) {
            val fileToMove = output[i]
            run findSpace@{
                (0 until i).forEach {
                    val filePart = output[it]
                    if (filePart.type == Type.FREESPACE && filePart.size >= fileToMove.size) {
                        output.add(it, fileToMove)
                        fileToMove.moved = true
                        filePart.size -= fileToMove.size
                        i++
                        output[i] = FreeSpace(fileToMove.size)
                        return@findSpace
                    }
                }
            }
        }
        i--
    }
    println(output)
    var checkSum = BigInteger.ZERO
    var k = 0
    output.forEach { filePart ->
        if (filePart.type == Type.FILE) {
            val file = filePart as FilePart
            checkSum += (k until k + file.size).sumOf { it.toBigInteger() * file.index.toBigInteger() }
        }
        k += filePart.size
    }
    println(checkSum)
    return checkSum
}


private fun readInput(): List<FileSystemPart> {
    val input = File("src/main/kotlin/day9/input.txt").readText()
    val output = mutableListOf<FileSystemPart>()
    var k = 0
    input.forEachIndexed { index, c ->
        val n = c.digitToInt()
        if (index % 2 == 0) {
            output.add(FilePart(false, n, k))
            k++
        } else {
            output.add(FreeSpace(n))
        }
    }

    return output
}

fun main() {
    val input = readInput()
////    val result = compact(input)
////    println("Answer to day 9 problem 1: ${result}")
//    val input = "00992111777.44.333....5555.6666.....8888.."
////    val input = "0099211177744333555566668888"
//    val checkSum = input.withIndex().sumOf { (index, c) -> (c.digitToIntOrNull() ?:0).toBigInteger() * index.toBigInteger() }
    println(input)
    compact(input.toMutableList())
}