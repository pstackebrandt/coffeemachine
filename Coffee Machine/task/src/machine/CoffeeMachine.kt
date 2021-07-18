package machine

import machine.product.IngredientStore

const val showComments = false

class CoffeeMachine(private val ingredients: IngredientStore) {

    fun start() {
        if (showComments) println("start")
        var loop = true

        do {
            ingredients.printIngredients()
            repeat(2) { println() }
            val actionResult = Action().manageAction(ingredients)
            println()
            if (actionResult == ActionType.EXIT.name) loop = false
        } while (loop)
        if (showComments) println("end of start")
    }
}

