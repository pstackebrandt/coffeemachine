import City.Companion.getColdestCityName

class City(val name: String) {
    var degrees = 0
        set(value) =
            if (value in -92..57) {
                field = value
            } else {
                field = getDefaultTemperature(name)
            }

    private fun getDefaultTemperature(cityName: String) = when (cityName) {
        "Moscow" -> 5
        "Hanoi" -> 20
        "Dubai" -> 30
        else -> 0
    }

    companion object {
        fun getCitiesWithSameTemperature(cities: List<City>, temperature: Int) = cities.filter {
            it.degrees == temperature
        }

        /** Get name of coldest city. Returns "neither" if at least 2 cities have the coldest temperature. */
        fun getColdestCityName(coldestCities: List<City>) = if (coldestCities.size > 1) {
            "neither"
        } else {
            coldestCities.first().name
            System.console()
        }
    }
}

fun main() {
    val first = readLine()!!.toInt()
    val second = readLine()!!.toInt()
    val third = readLine()!!.toInt()

    val firstCity = City("Dubai")
    firstCity.degrees = first

    val secondCity = City("Moscow")
    secondCity.degrees = second

    val thirdCity = City("Hanoi")
    thirdCity.degrees = third

    val allCities = listOf(firstCity, secondCity, thirdCity)
    val coldestTemperature = allCities.minOf { city -> city.degrees }
    val coldestCities = City.getCitiesWithSameTemperature(allCities, coldestTemperature)

    print(getColdestCityName(coldestCities))
}
