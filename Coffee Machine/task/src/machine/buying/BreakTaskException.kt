package machine.buying

/** Will be thrown to break a task or action,
 * e.g. to return from a menu without choosing a product.*/
class BreakTaskException(message: String) : Exception(message)