package machine.buying

import machine.ActionType
import machine.InputGetter
import machine.product.ProductMaker
import machine.order.Order
import machine.order.Part
import machine.product.IngredientStore
import machine.product.ProductType
import java.util.*

/** Allows to buy something. Before we can sell,
 * we ask for an order and produce the products. */
class Buying(private var ingredients: IngredientStore) {

    /** Let user buy something (includes getting order, producing and selling). */
    fun buy() {
        val order: Order
        try {
            order = getOrder()
        } catch (exc: BreakTaskException) {
            return
        }
        val maker = ProductMaker(ingredients)
        val processedOrder = maker.processOrder(order)
        val revenueMoney = sellProducts(processedOrder)
        ingredients.money += revenueMoney
    }

    /** Acquire order of 1 specific coffee from console. Must return 1. */
    private fun getOrder(): Order {
        println(
            "What do you want to buy? " +
                    "${ProductType.ESPRESSO.id} - ${ProductType.ESPRESSO.text}, " +
                    "${ProductType.LATTE.id}  - ${ProductType.LATTE.text}, " +
                    "${ProductType.CAPPUCCINO.id} - ${ProductType.CAPPUCCINO.text}, " +
                    "${ActionType.EXIT.description.lowercase(Locale.getDefault())}:"
        )

        do {
            when (val productName = InputGetter.getCommandText()) {
                ProductType.ESPRESSO.id.toString() -> return Order(Part(ProductType.ESPRESSO))
                ProductType.LATTE.id.toString() -> return Order(Part(ProductType.LATTE))
                ProductType.CAPPUCCINO.id.toString() -> return Order(Part(ProductType.CAPPUCCINO))
                ActionType.EXIT.text.first(), ActionType.EXIT.text.last(),
                ActionType.EXIT.shortcut.first(), ActionType.EXIT.shortcut.last() -> throw BreakTaskException("No order intended.")

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
