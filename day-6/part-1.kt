import java.io.File

fun main() {
    val input = File("/Users/vadiole/input.txt").readText()
    val map: Array<Array<String>> = input
        .lines()
        .map { row -> row.chunked(1).toTypedArray() }
        .toTypedArray()
    var speedV = -1
    var speedH = 0
    var positionV = 0
    var positionH = 0
    for (i in map.indices) {
        val row = map[i]
        for (j in row.indices) {
            val tile = row[j]
            if (tile == "^") {
                positionV = i
                positionH = j
            }
        }
    }
    while (true) {
        map[positionV][positionH] = "X"
        // move the guard in the current direction until she hits the edge of the map
        val nextPositionV = positionV + speedV
        val nextPositionH = positionH + speedH
        if (nextPositionV < 0 || nextPositionV >= map.size || nextPositionH < 0 || nextPositionH >= map[0].size) {
            break
        }
        val nextTile = map[nextPositionV][nextPositionH]
        // if hit an obstacle, rotate to the right
        if (nextTile == "#") {
            val lastSpeedV = speedV
            speedV = speedH
            speedH = lastSpeedV * -1
            continue
        }
        positionV = nextPositionV
        positionH = nextPositionH
    }
    var sum = 0
    for (i in map.indices) {
        for (j in map[i].indices) {
            if (map[i][j] == "X") {
                sum++
            }
        }
    }
    println(sum)
}
