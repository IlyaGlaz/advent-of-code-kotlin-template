package day01

import println
import readInput
import kotlin.math.abs

fun part1(list: List<Pair<Long, Long>>): Long {
    val (left, right) = list.unzip()

    val result = left.sorted().zip(right.sorted()).sumOf { abs(it.first - it.second) }

    return result
}

fun part2(list: List<Pair<Long, Long>>): Long {
    val (left, right) = list.unzip()

    val ids = left.toSet()
    val similarIdsCount = right.filter { ids.contains(it) }.groupingBy { it }.eachCount()
    val result = similarIdsCount.map { it.key * it.value }.sum()

    return result
}

fun main() {
    val input = readInput("day01/Day01")
    part1(getListOfPairs(input)).println()
    part2(getListOfPairs(input)).println()
}

private fun getListOfPairs(input: List<String>): List<Pair<Long, Long>> =
    input.map { line -> line.split("""\s+""".toRegex()).let { it[0].toLong() to it[1].toLong() } }
