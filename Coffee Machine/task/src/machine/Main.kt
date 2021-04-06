package machine

fun main() {
    val ingredients = Ingredients(waterMl = 400, coffeeBeans = 120, milkMl = 540, cups = 9, money = 550)
    CoffeeMachine(ingredients).start()
}
