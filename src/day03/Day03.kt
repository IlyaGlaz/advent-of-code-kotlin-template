package day03

import println
import readInputAsString

private const val mulRegex = """mul\((\d{1,3}),(\d{1,3})\)"""
private const val doRegex = """do\(\)"""
private const val dontRegex = """don't\(\)"""

fun main() {
    val input = readInputAsString("day03/Day03")
    part1(input).println()
    part2(input).println()
}

fun part1(input: String): Long {
    val matchResultList = mulRegex.toRegex().findAll(input)
    return matchResultList.sumOf { it.multiplyNumbers() }
}

fun part2(input: String): Long {
    var sum = 0L
    var enabled = true

    """$mulRegex|$doRegex|$dontRegex""".toRegex().findAll(input).forEach { match ->
        when (match.value) {
            "do()" -> enabled = true
            "don't()" -> enabled = false
            else -> if (enabled) sum += match.multiplyNumbers()
        }
    }

    return sum
}

fun MatchResult.multiplyNumbers(): Long {
    val (a, b) = destructured
    return a.toLong() * b.toLong()
}
