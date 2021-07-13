package machine

import machine.order.Order
import machine.order.Part

/** Allows to buy something */
class Buying(var ingredients: Ingredients) {

    fun buy() {
        getOrder() // may later return list of ordered products: Product + Amount
        // make product (remove ingredients)
        // sell product (add money)
    }

    /** Acquire order of 1 coffee from console. Must return 1. */
    private fun getOrder(): Order {
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:")

        do {
            var product = InputGetter.getCommandText()
            when (product) {
                ProductType.ESPRESSO.id.toString() -> return Order(Part(ProductType.ESPRESSO))
                ProductType.LATTE.id.toString() -> return Order(Part(ProductType.LATTE))
                ProductType.CAPPUCCINO.id.toString() -> return Order(Part(ProductType.CAPPUCCINO))
                else -> {
                    println("getOrder() Product $product not supported.")
                }
            }
        } while (true)
    }
}
