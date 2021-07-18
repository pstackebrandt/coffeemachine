package machine

import machine.product.IngredientStore
import java.util.*
import kotlin.collections.ArrayList

enum class ActionType(val text: ArrayList<String>, val description: String, val shortcut: ArrayList<String>) {
    BUY(arrayListOf("buy"), "Buy something", arrayListOf("b")),
    FILL(arrayListOf("fill"), "Refill ingredients and cups", arrayListOf("f")),
    TAKE(arrayListOf("take"), "Take money from machine", arrayListOf("t")),
    REMAINING(arrayListOf("remaining"), "Take money from machine", arrayListOf("r")),
    EXIT(arrayListOf("exit", "back"), "Back - to main menu", arrayListOf("e", "b")),
}

/** Lets you choose, start and return from a task of type [ActionType]*/
class Action {
    /** Ask for action, fulfill action*/
    fun manageAction(ingredients: IngredientStore): String {
        val action: ActionType = askForAction()
        if (showComments) println("Action: $action")
        return doAction(action, ingredients)
    }

    /** Do expected action or nothing. */
    private fun doAction(action: ActionType, ingredients: IngredientStore): String {
        when (action) {
            ActionType.BUY -> Buying(ingredients).buy()
            ActionType.FILL -> ingredients.fillStore()
            ActionType.TAKE -> ingredients.take()
            ActionType.REMAINING -> ingredients.printIngredients()
            ActionType.EXIT -> return ActionType.EXIT.name
        }
        return "ok"
    }

    private fun askForAction(): ActionType {
        println("Write action (buy, fill, take, remaining, exit):")

        do {
            val input = InputGetter.getCommandText()
                .lowercase(Locale.getDefault())

            when (input) {
                ActionType.BUY.text.first(), ActionType.BUY.shortcut.first() -> return ActionType.BUY
                ActionType.FILL.text.first(), ActionType.FILL.shortcut.first() -> return ActionType.FILL
                ActionType.TAKE.text.first(), ActionType.TAKE.shortcut.first() -> return ActionType.TAKE
                ActionType.REMAINING.text.first(), ActionType.REMAINING.shortcut.first() -> return ActionType.REMAINING
                ActionType.EXIT.text.first(), ActionType.EXIT.shortcut.first() -> return ActionType.EXIT
            }
        } while (true)
    }
}