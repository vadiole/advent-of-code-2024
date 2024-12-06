import java.io.File

fun main() {
    val input = File("/Users/vadiole/input.txt").readText()
    val mas = arrayOf("M", "A", "S")
    val directions = arrayOf(1 to 1, 1 to -1, -1 to 1, -1 to -1)
    val table = input.lines().map { it.chunked(1) }
    var sum = 0
    for (i in table.indices) {
        val row = table[i]
        for (j in row.indices) {
            var oneDirectionFound = false
            for (k in directions.indices) {
                for (w in mas.indices) {
                    val wCenter = w - mas.size / 2
                    val (directionI, directionJ) = directions[k]
                    val iTest = i + wCenter * directionI
                    val jTest = j + wCenter * directionJ
                    if (iTest !in table.indices || jTest !in row.indices) {
                        break
                    }
                    if (table[iTest][jTest] != mas[w]) {
                        break
                    }
                    if (w == mas.size - 1) {
                        if (oneDirectionFound) {
                            oneDirectionFound = false
                            sum++
                        } else {
                            oneDirectionFound = true
                        }
                    }
                }
            }
        }
    }
    println(sum)
}
