package day4

private fun findXmas(input: Array<CharArray>): Int {
    val directions = listOf<(Int, Int) -> String>(
        { i, j -> if (i < 3) "" else input.slice(i - 1 downTo i - 3).map { it[j] }.joinToString("") },
        { i, j -> if (i < 3 || j >= input[0].size - 3) "" else "" + input[i - 1][j + 1] + input[i - 2][j + 2] + input[i - 3][j + 3] },
        { i, j -> if (j >= input[0].size - 3) "" else input[i].slice(j + 1..j + 3).joinToString("") },
        { i, j -> if (i >= input.size - 3 || j >= input[0].size - 3) "" else "" + input[i + 1][j + 1] + input[i + 2][j + 2] + input[i + 3][j + 3] },
        { i, j -> if (i >= input.size - 3) "" else input.slice(i + 1..i + 3).map { it[j] }.joinToString("") },
        { i, j -> if (i >= input.size - 3 || j < 3) "" else "" + input[i + 1][j - 1] + input[i + 2][j - 2] + input[i + 3][j - 3] },
        { i, j -> if (j < 3) "" else input[i].slice(j - 1 downTo j - 3).joinToString("") },
        { i, j -> if (i < 3 || j < 3) "" else "" + input[i - 1][j - 1] + input[i - 2][j - 2] + input[i - 3][j - 3] }
    )

    var count = 0
    input.forEachIndexed { i, row ->
        row.forEachIndexed { j, char ->
            if (char == 'X') {
                for (direction in directions) {
                    if (direction(i, j) == "MAS") {
                        count++
                    }
                }
            }
        }
    }
    return count
}


fun main() {
    val input = readInput()
    println("Answer to day 4 problem 1: ${findXmas(input)}")
}