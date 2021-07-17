package machine

import machine.product.IngredientStore

enum class ActionType(val text: String, val description: String) {
    BUY("buy", "Buy something"),
    FILL("fill", "Refill ingredients and cups"),
    TAKE("take", "Take money from machine"),
}

class Action {
    /** Ask for action, fulfill action*/
    fun manageAction(ingredients: IngredientStore) {
        val action: ActionType = askForAction()
        doAction(action, ingredients)

        if (showComments) println("Action: $action")
    }

    /** Do expected action or nothing. */
    private fun doAction(action: ActionType, ingredients: IngredientStore) {
        when (action) {
            ActionType.BUY -> Buying(ingredients).buy()
            ActionType.FILL -> ingredients.fillStore()
            ActionType.TAKE -> ingredients.take()
            else -> {
                println("doAction: ActionType ${action.name} not supported")
            }
        }
    }

    private fun askForAction(): ActionType {
        println("Write action (buy, fill, take):")

        do {
            val input = InputGetter.getCommandText()
                .toLowerCase()

            when (input) {
                ActionType.BUY.text -> return ActionType.BUY
                ActionType.FILL.text -> return ActionType.FILL
                ActionType.TAKE.text -> return ActionType.TAKE
            }
        } while (true)
    }
}