package machine

import machine.product.IngredientStore

fun main() {
    val ingredients = IngredientStore(waterMl = 400, coffeeBeans = 120, milkMl = 540, cups = 9, money = 550)
    CoffeeMachine(ingredients).start()
}
