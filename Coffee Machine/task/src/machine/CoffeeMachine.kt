package machine

class CoffeeMachine {
    fun loopMenu() {
        var loop: Boolean

        do {
            printIngredients(howMuchCoffee())
            loop = false
        } while (loop)
    }

    private fun printIngredients(coffeeCount: Int) {
        print(getIngredientsMessage(coffeeCount))
    }

    private fun getIngredientsMessage(coffeeCount: Int): String {
        val waterInMl = coffeeCount * 200
        val beansInG = coffeeCount * 15
        val milkInMl = coffeeCount * 50

        return """
            For $coffeeCount cups of coffee you will need:
            $waterInMl ml of water
            $milkInMl ml of milk
            $beansInG g of coffee beans
            """.trimIndent()
    }

    private fun howMuchCoffee(): Int {
        println("Write how many cups of coffee you will need:")
        val cups = readLine()?.toIntOrNull()
        if (cups != null && cups >= 0) {
            return cups
        }
        return 0 // todo Fehlerbehandlung
    }
}
