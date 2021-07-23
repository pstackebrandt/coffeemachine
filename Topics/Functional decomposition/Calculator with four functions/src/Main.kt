// Implement your functions here
fun subtractTwoNumbers(a: Long, b: Long) = (a - b)
    .also {
        println(it)
    }

fun sumTwoNumbers(a: Long, b: Long) = (a + b)
    .also {
        println(it)
    }

fun divideTwoNumbers(a: Long, b: Long): Long = if (b == 0L) {
    println("Division by 0!")
    0L // It would be better to throw exception.
} else {
    (a / b).also {
        println(it)
    }
}

fun multiplyTwoNumbers(a: Long, b: Long) = (a * b)
    .apply {
        println(this)
    }