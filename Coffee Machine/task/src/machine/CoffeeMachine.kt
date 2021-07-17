package machine

import machine.product.Ingredient
import machine.product.IngredientStore

const val showComments = false

class CoffeeMachine(private val ingredients: IngredientStore) {

    fun start() {
        if(showComments) println("start")
        var loop: Boolean

        do {
            printIngredients()
            Action().manageAction(ingredients)
            //sumIngredients(getAdditionalIngredients())
            //printPossibleCoffeeAnswer(howMuchCoffeeRequired())
            printIngredients()
            loop = false // currently no looping required
        } while (loop)
        if(showComments) println("end of start")
    }

    private fun getAdditionalIngredients(): IngredientStore {
        val additionalIngredients = IngredientStore(money = 550, cups = 9)

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

    private fun getIngredientsMessage(ingredients: IngredientStore, coffeeCount: Int = 0): String {

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

    companion object {
        const val WaterMlPerCoffeeCup = 200
        const val BeansPerCoffeeCup = 15
        const val MilkMlPerCoffeeCup = 50
        const val CoffeeCup = 1
        const val Price = 7
    }
}

