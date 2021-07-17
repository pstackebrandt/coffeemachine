package machine.order

import machine.product.ProductType

/** Single part of an order. E.g. 2 espressos.
 * @param [isProduced] tells whether product was created already. */
data class Part(
    val product: ProductType,
    var amount: Int = 1,
    var isProduced: Boolean = false
)

/** Full order. E.g. 2 espressos, 1 latte */
data class Order(val parts: MutableList<Part> = mutableListOf()) {
    constructor(part: Part) : this(mutableListOf(part))
}