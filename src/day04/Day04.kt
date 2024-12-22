package day04

import println
import readInput

data class Vec(val x: Int, val y: Int)

private val directions = listOf(
    Vec(0, 1),
    Vec(1, 0),
    Vec(-1, 0),
    Vec(0, -1),
    Vec(1, 1),
    Vec(1, -1),
    Vec(-1, 1),
    Vec(-1, -1)
)

private fun getAtPos(x: Int, y: Int, list: List<List<Char>>): Char? = list.getOrNull(x)?.getOrNull(y)

private fun countXmasWords(list: List<List<Char>>): Int {
    var count = 0

    directions.forEach { direction ->
        for (i in list.indices) {
            for (j in list[0].indices) {
                if (isValidXmasWordForDirection(list, i, j, direction)) count++
            }
        }
    }

    return count
}

private fun isValidXmasWordForDirection(list: List<List<Char>>, startX: Int, startY: Int, direction: Vec): Boolean {
    var currentX = startX
    var currentY = startY

    for (char in listOf('X', 'M', 'A', 'S')) {
        if (getAtPos(currentX, currentY, list) != char) return false

        currentX += direction.x
        currentY += direction.y
    }

    return true
}

private fun countMasWordsInXForm(list: List<List<Char>>): Int {
    val masWords = listOf(listOf('M', 'A', 'S'), listOf('S', 'A', 'M'))

    var count = 0

    for (i in list.indices) {
        for (j in list[0].indices) {
            if (masWords.any { isValidMasWordInXForm(list, i, j, it, Vec(1, 1)) }
                && masWords.any { isValidMasWordInXForm(list, i, j + 2, it, Vec(1, -1)) })
                count++
        }
    }

    return count
}

private fun isValidMasWordInXForm(
    list: List<List<Char>>,
    startX: Int,
    startY: Int,
    word: List<Char>,
    direction: Vec
): Boolean {
    var currentX = startX
    var currentY = startY

    for (char in word) {
        if (getAtPos(currentX, currentY, list) != char) return false

        currentX += direction.x
        currentY += direction.y
    }

    return true
}

fun part1(list: List<List<Char>>): Int = countXmasWords(list)

fun part2(list: List<List<Char>>): Int = countMasWordsInXForm(list)

fun main() {
    val input = readInput("day04/Day04")
    part1(listOfLists(input)).println()
    part2(listOfLists(input)).println()
}

private fun listOfLists(input: List<String>): List<List<Char>> = input.map { line -> line.toCharArray().toList() }
