class City(val name: String) {
    var population = 0
        set(value)  {
            field = maxOf(0, minOf(50_000_000, value))
        }
}