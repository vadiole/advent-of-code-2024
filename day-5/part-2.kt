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
        .mapNotNull { page ->
            val fixedPage = page.toMutableList()
            var wasFixed = false
            fixedPage.sortWith { second, first ->
                val hasBrokenRule = rules.any { (ruleFirst, ruleSecond) ->
                    first == ruleSecond && second == ruleFirst
                }
                if (hasBrokenRule) {
                    wasFixed = true
                    -1
                } else {
                    1
                }
            }
            if (wasFixed) {
                fixedPage
            } else {
                null
            }
        }
        .sumOf { page ->
            page[page.size / 2]
        }
    println(sum)
}
