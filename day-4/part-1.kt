import java.io.File

fun main() {
    val input = File("/Users/vadiole/input.txt").readText()
    val xmas = arrayOf("X", "M", "A", "S")
    val directions = arrayOf(1 to 0, 0 to 1, -1 to 0, 0 to -1, 1 to 1, -1 to 1, 1 to -1, -1 to -1)
    val table = input.lines().map { it.chunked(1) }
    var sum = 0
    for (i in table.indices) {
        val row = table[i]
        for (j in row.indices) {
            for (k in directions.indices) {
                for (w in xmas.indices) {
                    val (directionI, directionJ) = directions[k]
                    val iTest = i + w * directionI
                    val jTest = j + w * directionJ
                    if (iTest !in table.indices || jTest !in row.indices) {
                        break
                    }
                    if (table[iTest][jTest] != xmas[w]) {
                        break
                    }
                    if (w == xmas.size - 1) {
                        sum++
                    }
                }
            }
        }
    }
    println(sum)
}
