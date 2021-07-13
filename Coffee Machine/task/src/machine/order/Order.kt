package machine.order

import machine.ProductType

/** Single part of an order. E.g. 2 espressos. */
data class Part(
    val product: ProductType,
    val amount: Int = 1
)

/** Full order. E.g. 2 espressos, 1 latte */
data class Order(val parts: MutableList<Part> = mutableListOf()) {
    constructor(part: Part) : this(mutableListOf(part))
}