package machine

import machine.product.IngredientStore

const val showComments = false

/** A machine, that sells coffee. To be able to do it, it asks for an order and
 * produces. It has ingredients and gets money for each beverage sold.*/
class CoffeeMachine(private val ingredients: IngredientStore) {

    fun start() {
        if (showComments) println("start")
        var loop = true

        do {
            repeat(2) { println() }
            val actionResult = Action().manageAction(ingredients)
            println()
            if (actionResult == ActionType.EXIT.name) loop = false
        } while (loop)
        if (showComments) println("end of start")
    }
}

