import java.text.SimpleDateFormat
import java.util.Date
import java.util.Scanner
// Clasa care implementează metoda de plată prin card
class CardPayment(private val bankAccount: BankAccount) : PaymentMethod {
    // Suprascrierea metodei 'pay' din interfața PaymentMethod
    override fun pay(fee: Double): Boolean {
        // Folosim instanța 'bankAccount' pentru a apela updateAmount
        return bankAccount.updateAmount(-fee) // Scădem taxa (fee) din soldul contului bancar
    }
}
