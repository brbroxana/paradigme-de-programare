import java.text.SimpleDateFormat
import java.util.Date
import java.util.Scanner

// Clasa care reprezintă un cont bancar
class BankAccount(
    private var availableAmount: Double, // Suma disponibilă în cont
    private val cardNumber: String, // Numărul cardului
    private val expirationDate: Date, // Data de expirare a cardului
    private val cvvCode: Int, // Codul CVV al cardului
    private val userName: String // Numele utilizatorului
) {
    // Metodă pentru actualizarea sumei disponibile în cont
    fun updateAmount(value: Double): Boolean {
        availableAmount += value
        return true
    }

    // Metodă pentru obținerea sumei disponibile
    fun getAvailableAmount() = availableAmount
}
