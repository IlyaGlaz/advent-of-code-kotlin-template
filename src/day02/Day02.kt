package day02

import println
import readInput

fun main() {
    val input = readInput("day02/Day02")
    part1(listOfReports(input)).println()
    part2(listOfReports(input)).println()
}

fun isSafe(levels: List<Int>): Boolean {
    val difference = levels.zipWithNext { a, b -> a - b }

    return difference.all { it in -3..3 }
            && (difference.all { it > 0 } || difference.all { it < 0 })
}

fun part1(input: List<List<Int>>): Int =
    input.count(::isSafe)


fun part2(input: List<List<Int>>): Int =
    input.count { numbers ->
        numbers.indices.any {
            val skipped = numbers.toMutableList().apply { removeAt(it) }
            isSafe(skipped)
        }
    }

fun listOfReports(input: List<String>): List<List<Int>> = input.map { line ->
    line.split("""\s""".toRegex()).map { it.toInt() }
}
