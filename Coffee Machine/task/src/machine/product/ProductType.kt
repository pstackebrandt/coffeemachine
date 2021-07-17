package machine.product

enum class ProductType(val text: String, val id: Int, val ingredients: IngredientStore) {
    ESPRESSO("Espresso", 1, IngredientStore(250, 0, 16, 4, 1)),
    LATTE("Latte", 2, IngredientStore(350, 75, 20, 7, 1)),
    CAPPUCCINO("Cappuccino", 3, IngredientStore(200, 100, 12, 6, 1)),
}
