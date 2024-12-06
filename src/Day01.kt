import kotlin.math.abs

fun main() {
    fun getListOfPairs(input: List<String>): List<Pair<Long, Long>> =
        input.map { line -> line.split("""\s+""".toRegex()).let { it[0].toLong() to it[1].toLong() } }

    fun part1(input: List<String>): Long {
        val (left, right) = getListOfPairs(input).unzip()

        val result = left.sorted().zip(right.sorted()).sumOf { abs(it.first - it.second) }

        return result
    }

    fun part2(input: List<String>): Long {
        val (left, right) = getListOfPairs(input).unzip()

        val ids = left.toSet()
        val similarIdsCount = right.filter { ids.contains(it) }.groupingBy { it }.eachCount()
        val result = similarIdsCount.map { it.key * it.value }.sum()

        return result
    }

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
