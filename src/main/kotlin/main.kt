import kotlin.math.roundToInt

fun main() {
    println(calculateCommission(amount = 15000.0))
}

fun calculateCommission(accountType: String = "Vk Pay", totalAmount: Double = 0.0, amount: Double): Double {
    if (totalAmount + amount > 600000.0) throw IllegalArgumentException("Unexpected sum")

    return when (accountType) {
        "Mastercard", "Maestro" -> if (totalAmount + amount <= 75000.0) 0.0 else ((amount * 0.006 + 20) * 100).roundToInt() / 100.0
        "Visa", "Mir" -> if (amount * 0.0075 > 35.0) (amount * 0.0075 * 100).roundToInt() / 100.0 else 35.0
        "Vk Pay" -> if (totalAmount + amount <= 40000.0 && amount <= 15000.0) 0.0 else throw IllegalArgumentException("Unexpected sum")
        else -> throw IllegalArgumentException("Unexpected account type")
    }
}