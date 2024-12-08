import java.io.File

fun main() {
    val input = File("/Users/vadiole/input.txt").readText()
    val map: Array<Array<String>> = input
        .lines()
        .map { row -> row.chunked(1).toTypedArray() }
        .toTypedArray()

    var initialX = 0
    var initialY = 0
    for (i in map.indices) {
        for (j in map[i].indices) {
            if (map[i][j] == "^") {
                initialX = i
                initialY = j
            }
        }
    }
    var loops = 0
    for (i in map.indices) {
        for (j in map[i].indices) {
            if (map[i][j] != ".") {
                continue
            }
            map[i][j] = "#"
            if (isLoop(map, initialX, initialY)) {
                loops++
            }
            map[i][j] = "."
        }
    }
    println(loops)
}

fun isLoop(map: Array<Array<String>>, initial: Pair<Int, Int>): Boolean {
    var direction = 0
    val delta = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    val visited = mutableSetOf<Triple<Int, Int, Int>>()
    var (x, y) = initial
    while (true) {
        if (visited.contains(Triple(x, y, direction))) {
            return true
        }
        visited.add(Triple(x, y, direction))
        val (dX, dY) = delta[direction]
        val nextX = x + dX
        val nextY = y + dY
        if (nextX < 0 || nextX >= map.size || nextY < 0 || nextY >= map[nextX].size) {
            break
        }
        if (map[nextX][nextY] == "#") {
            direction = (direction + 1) % delta.size
            continue
        }
        x = nextX
        y = nextY
    }
    return false
}
