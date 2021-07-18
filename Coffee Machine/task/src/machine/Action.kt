package machine

import machine.product.IngredientStore

enum class ActionType(val text: String, val description: String) {
    BUY("buy", "Buy something"),
    FILL("fill", "Refill ingredients and cups"),
    TAKE("take", "Take money from machine"),
    REMAINING("remaining", "Take money from machine"),
    EXIT("exit", "Back - to main menu"),
}

class Action {
    /** Ask for action, fulfill action*/
    fun manageAction(ingredients: IngredientStore) : String {
        val action: ActionType = askForAction()
        if (showComments) println("Action: $action")
        return doAction(action, ingredients)
    }

    /** Do expected action or nothing. */
    private fun doAction(action: ActionType, ingredients: IngredientStore) : String{
        when (action) {
            ActionType.BUY -> Buying(ingredients).buy()
            ActionType.FILL -> ingredients.fillStore()
            ActionType.TAKE -> ingredients.take()
            ActionType.REMAINING -> ingredients.printIngredients()
            ActionType.EXIT -> return ActionType.EXIT.name
            else -> {
                println("doAction: ActionType ${action.name} not supported")
            }
        }
        return "ok"
    }

    private fun askForAction(): ActionType {
        println("Write action (buy, fill, take, remaining, exit):")

        do {
            val input = InputGetter.getCommandText()
                .toLowerCase()

            when (input) {
                ActionType.BUY.text -> return ActionType.BUY
                ActionType.FILL.text -> return ActionType.FILL
                ActionType.TAKE.text -> return ActionType.TAKE
                ActionType.REMAINING.text -> return ActionType.REMAINING
                ActionType.EXIT.text -> return ActionType.EXIT
            }
        } while (true)
    }
}