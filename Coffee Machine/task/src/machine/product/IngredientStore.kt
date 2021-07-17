package machine.product

/** Container for all ingredients used to make a beverage.*/
data class IngredientStore(
    var waterMl: Int = 0,
    var milkMl: Int = 0,
    var coffeeBeans: Int = 0,
    var money: Int = 0,
    var cups: Int = 0
) {

    /** Add ingredients to store. */
    fun fillStore() {
        askForIngredientAmountToAdd(Ingredient.Water)?.run {
            waterMl += this
        }
        askForIngredientAmountToAdd(Ingredient.CoffeeBeans)?.run {
            coffeeBeans += this
        }
        askForIngredientAmountToAdd(Ingredient.Milk)?.run {
            milkMl += this
        }
        askForIngredientAmountToAdd(Ingredient.Cups)?.run {
            cups += this
        }
    }

    private fun askForIngredientAmountToAdd(type: Ingredient): Int? {

        println(
            when (type) {
                Ingredient.Cups -> "Write how many " +
                        "${type.longNamePlural} you want to add:"
                Ingredient.Water,
                Ingredient.CoffeeBeans,
                Ingredient.Milk -> "Write how many ${type.unitPluralShort} of " +
                        "${type.longNamePlural} you want to add:"
                Ingredient.Money -> "Money is unexpected"
            }
        )
        return readLine()!!.toIntOrNull()
    }

    fun printIngredients() {
        print(getIngredientsMessage())
    }

    private fun getIngredientsMessage() = """
            The coffee machine has:
            $waterMl of water
            $milkMl of milk
            $coffeeBeans of coffee beans
            $cups of disposable cups
            $money of money
            """.trimIndent()
}

/** Seems to define each ingredient. */
enum class Ingredient(
    val namePlural: String,
    val longNamePlural: String,
    val unitPluralShort: String
) {
    Water("water", "water", "ml"),
    Milk("milk", "milk", "ml"),
    CoffeeBeans("coffee beans", "coffee beans", "grams"),
    Cups("disposable cups", "disposable cups of coffee", ""),
    Money("money", "money", "")
}