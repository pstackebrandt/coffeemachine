package machine

/** Thrown to break a task or action,
 * e.g. to return from a menu without choosing a product.*/
class BreakTaskException : Exception {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
    constructor(cause: Throwable) : super(cause)
}