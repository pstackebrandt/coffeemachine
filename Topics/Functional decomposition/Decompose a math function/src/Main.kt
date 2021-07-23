fun f(x: Double) = when {
    x <= 0 -> f1(x)
    x >= 1 -> f3(x)
    else -> f2(x)
}

// implement your functions here
fun f1(x: Double) = x * x + 1
fun f2(x: Double) = 1 / (x * x)
fun f3(x: Double) = x * x - 1
