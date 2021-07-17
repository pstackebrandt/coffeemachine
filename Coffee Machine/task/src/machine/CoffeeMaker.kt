package machine

import machine.order.Order
import machine.order.Part
import machine.product.IngredientStore
import machine.product.ProductType
import kotlin.math.min


class CoffeeMaker(var ingredients: IngredientStore) {
    companion object {
        /** Returns how many pieces of the product in [product] could be produced
         * with the [storedIngredients].*/
        fun howManyPiecesProducible(
            storedIngredients: IngredientStore,
            product: ProductType
        ): Int {
            val partIngredients = product.ingredients

            val fromCups = storedIngredients.cups / partIngredients.cups
            val fromWater = storedIngredients.waterMl / partIngredients.waterMl
            val fromBeans = storedIngredients.coffeeBeans / partIngredients.coffeeBeans

            val fromMilk =
                if (partIngredients.milkMl == 0) {
                    Int.MAX_VALUE
                } else storedIngredients.milkMl / partIngredients.milkMl

            return minOf(fromCups, fromWater, fromBeans, fromMilk)
        }
    }

//    private fun printPossibleCoffeeAnswer(requiredCoffeeCount: Int) {
//        println(getPossibleCoffeeAnswer(requiredCoffeeCount))
//    }

//    private fun getPossibleCoffeeAnswer(requiredCoffeeCount: Int): String {
//        val cupsCoffeeMadeCount = makeCupsOfCoffee(requiredCoffeeCount)
//        val furtherPossibleCoffeeCount = howManyPiecesProduceable(ingredients)
//
//        return when {
//            furtherPossibleCoffeeCount > 0 -> "Yes, I can make that amount of coffee " +
//                    "(and even $furtherPossibleCoffeeCount more than that)"
//            cupsCoffeeMadeCount < requiredCoffeeCount -> "No, I can make only $cupsCoffeeMadeCount cups of coffee"
//            else -> "Yes, I can make that amount of coffee"
//        }
//    }

    /** Create
     * @return produced drinks */
    fun makeDrinks(orderPart: Part): Part {
        val drinkAmountToMake = getDrinkAmountsToMake(orderPart)
        val ingredientsPerDrink = orderPart.product.ingredients

        // consume ingredients
        with(ingredients) {
            //
            this.cups = this.cups - drinkAmountToMake * ingredientsPerDrink.cups
            this.waterMl = this.waterMl - drinkAmountToMake * ingredientsPerDrink.waterMl
            this.coffeeBeans = this.coffeeBeans - drinkAmountToMake * ingredientsPerDrink.coffeeBeans
            this.milkMl = this.milkMl - drinkAmountToMake * ingredientsPerDrink.milkMl
        }

        // created drinks
        return Part(orderPart.product, drinkAmountToMake, true)
    }

    /** Tells how many drinks to make. Considers drink type, wished drink count and
     * available ingredients. Doesn't consume ingredients  */
    private fun getDrinkAmountsToMake(orderPart: Part): Int {
        val possibleAmount = howManyPiecesProducible(ingredients, orderPart.product)
        return min(orderPart.amount, possibleAmount)
    }


    private fun sumIngredients(additionalIngredients: IngredientStore) {
        ingredients.run {
            waterMl += additionalIngredients.waterMl
            milkMl += additionalIngredients.milkMl
            coffeeBeans += additionalIngredients.coffeeBeans
        }
    }

    private fun howMuchCoffeeRequired(): Int {
        println("Write how many cups of coffee you will need:")
        val cups = readLine()?.toIntOrNull()
        if (cups != null && cups >= 0) {
            return cups
        }
        return 0 // todo error handling
    }

    /** Process an [order] which contains expected products.
     * @return Order which contains created products and the not created products. */
    fun processOrder(order: Order): Order {
        val tag = "processOrder()"

        val toBeProduced = order.parts.filter { !it.isProduced; }

        toBeProduced.forEach { requiredProduct ->
            val producedDrinks: Part = makeDrinks(requiredProduct)
            requiredProduct.amount -= producedDrinks.amount

            order.parts.add(producedDrinks)

            if(showComments) println(
                "$tag producedProduct: ${producedDrinks.product.name}, " +
                        "produced: ${producedDrinks.amount}, " +
                        "required: ${requiredProduct.amount}"
            )
        }

        return order
    }
}