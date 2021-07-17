package machine

class InputGetter {

    companion object {

        /** Get trimmed string from console which is not empty.
         * */
        fun getCommandText(): String {
            var input: String?

            do {
                val inputLine: String? = readLine()

                input = inputLine?.trim()

            } while (input == null || input.isEmpty())

            return input
        }
    }
}