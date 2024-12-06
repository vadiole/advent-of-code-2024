import java.io.File

fun main() {
    val input = File("/Users/vadiole/input.txt").readText()
    val (rulesInput, pagesInput) = input.split("\n\n")
    val rules: List<List<Int>> = rulesInput
        .lines()
        .map { rule ->
            rule
                .split("|")
                .map(String::toInt)
        }
    val pages: List<List<Int>> = pagesInput
        .lines()
        .map { page ->
            page
                .split(",")
                .map(String::toInt)
        }
    val sum = pages
        .filter { page ->
            for (i in 0..page.size - 2) {
                val first = page[i]
                val second = page[i + 1]
                val hasBrokenRule = rules.any { (ruleFirst, ruleSecond) ->
                    first == ruleSecond && second == ruleFirst
                }
                if (hasBrokenRule) {
                    return@filter false
                }
            }
            true
        }
        .sumOf { page ->
            page[page.size / 2]
        }
    println(sum)
}
