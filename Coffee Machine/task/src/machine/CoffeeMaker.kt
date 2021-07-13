package machine

import kotlin.math.min


enum class ProductType(val text: String, val id: Int){
    ESPRESSO("Espresso", 1),
    LATTE("Latte", 2),
    CAPPUCCINO("Cappuccino", 3)
}

class CoffeeMaker(var ingredients: Ingredients) {
    private fun printPossibleCoffeeAnswer(requiredCoffeeCount: Int) {
        println(getPossibleCoffeeAnswer(requiredCoffeeCount))
    }

    private fun getPossibleCoffeeAnswer(requiredCoffeeCount: Int): String {
        val cupsCoffeeMadeCount = makeCupsOfCoffee(requiredCoffeeCount)
        val furtherPossibleCoffeeCount = howMuchCoffeePossible(ingredients)

        return when {
            furtherPossibleCoffeeCount > 0 -> "Yes, I can make that amount of coffee " +
                    "(and even $furtherPossibleCoffeeCount more than that)"
            cupsCoffeeMadeCount < requiredCoffeeCount -> "No, I can make only $cupsCoffeeMadeCount cups of coffee"
            else -> "Yes, I can make that amount of coffee"
        }
    }

    /** @return number of coffee cups cooked */
    private fun makeCupsOfCoffee(requiredCoffeeCount: Int): Int {
        val possibleCoffeeCups = howMuchCoffeePossible(ingredients)
        val cupToMake = min(requiredCoffeeCount, possibleCoffeeCups)

        ingredients.waterMl = ingredients.waterMl - cupToMake * CoffeeMachine.WaterMlPerCoffeeCup
        ingredients.coffeeBeans = ingredients.coffeeBeans - cupToMake * CoffeeMachine.BeansPerCoffeeCup
        ingredients.milkMl = ingredients.milkMl - cupToMake * CoffeeMachine.MilkMlPerCoffeeCup

        return cupToMake
    }

    private fun howMuchCoffeePossible(ingredients: Ingredients): Int {
        val coffeeFromWater = ingredients.waterMl / CoffeeMachine.WaterMlPerCoffeeCup
        val coffeeFromBeans = ingredients.coffeeBeans / CoffeeMachine.BeansPerCoffeeCup
        val coffeeFromMilk = ingredients.milkMl / CoffeeMachine.MilkMlPerCoffeeCup

        return minOf(coffeeFromWater, coffeeFromBeans, coffeeFromMilk)
    }

    private fun sumIngredients(additionalIngredients: Ingredients) {
        ingredients.run {
            waterMl += additionalIngredients.waterMl
            milkMl += additionalIngredients.milkMl
            coffeeBeans += additionalIngredients.coffeeBeans
        }
    }

    private fun howMuchCoffeeRequired(): Int {
        println("Write how many cups of coffee you will need:")
        val cups = readLine()?.toIntOrNull()
        if (cups != null && cups >= 0) {
            return cups
        }
        return 0 // todo error handling
    }
}