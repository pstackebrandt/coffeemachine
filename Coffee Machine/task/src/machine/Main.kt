package machine

fun main() {
    CoffeeMachine().loopMenu()

    val showAll = false
    if (showAll) {
        println("""
        Starting to make a coffee
        Grinding coffee beans
        Boiling water
        Mixing boiled water with crushed coffee beans
        Pouring coffee into the cup
        Pouring some milk into the cup
        Coffee is ready!
        """.trimIndent())
    }
}
