package machine

enum class OperationType(val text: String, val description: String) {
    BUY("buy", "Buy something"),
    FILL("fill", "Refill ingredients and cups"),
    TAKE("take", "Take money from machine"),
}

class Action {
    /** Ask for action, fulfill action*/
    fun manageAction() {
        // Ask for action
        val action = askForAction()
        // manage action

        println("Action: $action")
    }

    private fun askForAction(): OperationType {
        println("Write action (buy, fill, take):")

        do {
            val input = InputGetter.getCommandText()
                .toLowerCase()

            when (input) {
                OperationType.BUY.text -> return OperationType.BUY
                OperationType.FILL.text -> return OperationType.FILL
                OperationType.TAKE.text -> return OperationType.TAKE
            }
        } while (true)
    }
}