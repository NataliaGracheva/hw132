import org.junit.Assert.*
import org.junit.Test

class MainKtTest {

    @Test
    fun shouldCalculateCommissionForDefaultParameters() {
        val result = calculateCommission(amount = 15000.0)
        assertEquals(10.0, result, 0.0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun mustThrowExceptionWhenExceedingMaxAmountPerMonthForAll() {
        calculateCommission(totalAmount = 450001.0, amount = 150000.0)
    }

    @Test
    fun noCommissionIfMaxAmountPerMonthIsNotExceededForMastercardOrMaestro() {
        val result = calculateCommission("Mastercard", 74000.0, 1000.0)
        assertEquals(0.0, result, 0.0)
    }

    @Test
    fun shouldCalculateCommissionAccordingToRateForMastercardOrMaestro() {
        val result = calculateCommission("Maestro", 74001.0, 1000.0)
        assertEquals(26.0, result, 0.0)
    }

    @Test
    fun shouldCalculateCommissionAccordingToRateForVisaOrMir() {
        val result = calculateCommission("Visa", 0.0, 4700.0)
        assertEquals(35.25, result, 0.0)
    }

    @Test
    fun shouldReturnMinCommissionIfItIsMoreThanAccordingToRateForVisaOrMir() {
        val result = calculateCommission("Mir", 0.0, 1000.0)
        assertEquals(35.0, result, 0.0)
    }

    @Test
    fun noCommissionIfMaxAmountAndMaxAmountPerMonthIsNotExceededForVkPay() {
        val result = calculateCommission("Vk Pay", 25000.0, 15000.0)
        assertEquals(0.0, result, 0.0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun mustThrowExceptionWhenExceedingMaxAmountPerMonthForVkPay() {
        calculateCommission("Vk Pay", 39001.0, 1000.0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun mustThrowExceptionWhenExceedingMaxAmountForVkPay() {
        calculateCommission("Vk Pay", 0.0, 15001.0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun mustThrowExceptionIfAccountTypeIsUnexpected() {
        calculateCommission("UnionPay", 0.0, 1000.0)
    }
}