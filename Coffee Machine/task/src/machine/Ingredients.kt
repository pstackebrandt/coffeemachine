package machine

data class Ingredients(
        var waterMl: Int = 0,
        var milkMl: Int = 0,
        var coffeeBeans: Int = 0,
        val money: Int = 0,
        val cups: Int = 0)

enum class Ingredient(
        val namePlural: String,
        val unitPluralShort: String) {
    Water("water", "ml"),
    Milk("milk", "ml"),
    CoffeeBeans("coffee beans", "grams"),
    Cups("disposable cups", ""),
    Money("money", "")
}