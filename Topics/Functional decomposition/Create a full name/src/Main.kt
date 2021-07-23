// use this code as a source of inspiration for your function
fun getFullNames() {
    repeat(3) {
        val firstName = readLine()
        val lastName = readLine()
        println(createFullName(firstName, lastName))
    }
}

// implement your function here
fun createFullName(firstName: String?, lastName: String?) = "$firstName $lastName"