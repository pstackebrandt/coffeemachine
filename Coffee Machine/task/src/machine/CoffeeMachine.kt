package machine

import kotlin.math.min

class CoffeeMachine(private val ingredients: Ingredients) {

    fun start() {
        println("start")
        var loop: Boolean

        do {
            //printIngredients()
            Action().manageAction()
            //sumIngredients(getAdditionalIngredients())
            //printPossibleCoffeeAnswer(howMuchCoffeeRequired())
            loop = false // currently no looping required
        } while (loop)
        println("end of start")
    }

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

        ingredients.waterMl = ingredients.waterMl - cupToMake * WaterMlPerCoffeeCup
        ingredients.coffeeBeans = ingredients.coffeeBeans - cupToMake * BeansPerCoffeeCup
        ingredients.milkMl = ingredients.milkMl - cupToMake * MilkMlPerCoffeeCup

        return cupToMake
    }

    private fun howMuchCoffeePossible(ingredients: Ingredients): Int {
        val coffeeFromWater = ingredients.waterMl / WaterMlPerCoffeeCup
        val coffeeFromBeans = ingredients.coffeeBeans / BeansPerCoffeeCup
        val coffeeFromMilk = ingredients.milkMl / MilkMlPerCoffeeCup

        return minOf(coffeeFromWater, coffeeFromBeans, coffeeFromMilk)
    }

    private fun sumIngredients(additionalIngredients: Ingredients) {
        ingredients.run {
            waterMl += additionalIngredients.waterMl
            milkMl += additionalIngredients.milkMl
            coffeeBeans += additionalIngredients.coffeeBeans
        }
    }

    private fun getAdditionalIngredients(): Ingredients {
        val additionalIngredients = Ingredients(money = 550, cups = 9)

        getIngredientAmount(Ingredient.Water)?.run {
            additionalIngredients.waterMl += this
        }
        getIngredientAmount(Ingredient.Milk)?.run {
            additionalIngredients.milkMl += this
        }
        getIngredientAmount(Ingredient.CoffeeBeans)?.run {
            additionalIngredients.coffeeBeans += this
        }

        return additionalIngredients
    }

    private fun getIngredientAmount(type: Ingredient): Int? {
        println("Write how many ${type.unitPluralShort} of " +
                "${type.namePlural} the coffee machine has")
        return readLine()!!.toIntOrNull()
    }

    @Suppress("unused")
    private fun printIngredients(coffeeCount: Int = 0) {
        print(getIngredientsMessage(ingredients, coffeeCount))
    }

    private fun getIngredientsMessage(ingredients: Ingredients, coffeeCount: Int = 0): String {

        if (coffeeCount <= 0) {
            return ingredients.run {
                """
                The coffee machine has:
                $waterMl of water
                $milkMl of milk
                $coffeeBeans of coffee beans
                $cups of disposable cups
                $money of money
                """.trimIndent()
            }
        }

        val waterInMl = coffeeCount * WaterMlPerCoffeeCup
        val beansInG = coffeeCount * BeansPerCoffeeCup
        val milkInMl = coffeeCount * MilkMlPerCoffeeCup
        val money = coffeeCount * Price

        return """
            For $coffeeCount cups of coffee you will need:
            $waterInMl ml of water
            $milkInMl ml of milk
            $beansInG g of coffee beans
            ${ingredients.cups} of disposable cups
            $money of money
            """.trimIndent()
    }

    private fun howMuchCoffeeRequired(): Int {
        println("Write how many cups of coffee you will need:")
        val cups = readLine()?.toIntOrNull()
        if (cups != null && cups >= 0) {
            return cups
        }
        return 0 // todo error handling
    }

    companion object {
        const val WaterMlPerCoffeeCup = 200
        const val BeansPerCoffeeCup = 15
        const val MilkMlPerCoffeeCup = 50
        const val CoffeeCup = 1
        const val Price = 7

    }
}

