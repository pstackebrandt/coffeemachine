package machine

import machine.product.Ingredient
import machine.product.IngredientStore

const val showComments = false

class CoffeeMachine(private val ingredients: IngredientStore) {

    fun start() {
        if (showComments) println("start")
        var loop: Boolean

        do {
            ingredients.printIngredients()
            println()
            println()
            Action().manageAction(ingredients)
            println()
            //sumIngredients(getAdditionalIngredients())
            //printPossibleCoffeeAnswer(howMuchCoffeeRequired())
            ingredients.printIngredients()
            loop = false // currently no looping required
        } while (loop)
        if (showComments) println("end of start")
    }
}

