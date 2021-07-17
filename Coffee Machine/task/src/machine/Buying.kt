package machine

import machine.order.Order
import machine.order.Part
import machine.product.IngredientStore
import machine.product.ProductType

/** Allows to buy something */
class Buying(var ingredients: IngredientStore) {

    fun buy() {
        val order = getOrder() // may later return list of ordered products: Product + Amount
        val maker = CoffeeMaker(ingredients)
        val processedOrder = maker.processOrder(order)
        val revenueMoney = sellProducts(processedOrder)
        ingredients.money += revenueMoney
    }

    /** Acquire order of 1 specific coffee from console. Must return 1. */
    private fun getOrder(): Order {
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:")

        do {
            when (val productName = InputGetter.getCommandText()) {
                ProductType.ESPRESSO.id.toString() -> return Order(Part(ProductType.ESPRESSO))
                ProductType.LATTE.id.toString() -> return Order(Part(ProductType.LATTE))
                ProductType.CAPPUCCINO.id.toString() -> return Order(Part(ProductType.CAPPUCCINO))
                else -> {
                    println("getOrder() Product $productName not supported.")
                }
            }
        } while (true)
    }

    /** Get money for produced products */
    private fun sellProducts(processedOrder: Order): Int {
        val producedProducts = processedOrder.parts.filter {
            it.isProduced
        }

        var money = 0
        producedProducts.forEach { producedProduct ->
            money += producedProduct.amount * producedProduct.product.ingredients.money
        }
        return money
    }
}
