import java.text.SimpleDateFormat
import java.util.*

// Interfață pentru metodele de plată
interface PaymentMethod {
    fun pay(fee: Double) : Boolean
}

fun main() {
    val scanner = Scanner(System.`in`)
    // Solicităm utilizatorului să introducă suma disponibilă în cont
    println("Introduceti suma disponibila in cont: ")
    val availableAmount = scanner.nextDouble()

    // Solicităm numărul cardului
    println("Introducetu numarul cardului:")
    val cardNumber = scanner.next()

    // Solicităm data de expirare a cardului și o convertim într-un format Date
    println("Introduceti data de expirare a cardului (MM/YYYY):")
    val expirationDateStr = scanner.next()
    val expirationDate = SimpleDateFormat("MM/yyyy").parse(expirationDateStr)

    // Solicităm codul CVV al cardului
    println("Introduceti codul CVV:")
    val cvvCode = scanner.nextInt()

    // Solicităm numele utilizatorului
    println("Introduceti numele de utilizator:")
    val userName = scanner.next()

    // Creăm un cont bancar pe baza datelor introduse
    val bankAccount = BankAccount(availableAmount, cardNumber, expirationDate, cvvCode, userName)

    //card doar
    val cardPayment = CardPayment(bankAccount) // Inițializăm plata prin card
    // Solicităm prețul biletului
    println("Introduceti pretul biletului:")
    val ticketPrice = scanner.nextDouble()

    // Creăm un sistem de cumpărare de bilete utilizând plata prin card
    val cardTicketPurchaseSystem = TicketPurchaseSystem(cardPayment)
    val cardPaymentResult = cardTicketPurchaseSystem.purchaseTicket(ticketPrice)

    // Afișăm rezultatul plății cu cardul
    println("Rezultatul platii cu cardul: $cardPaymentResult")
    // Acum, permiterea platilor cu numerar
    println("Doriti sa platiti cu numerar? (da/nu)")
    val cashPaymentChoice = scanner.next()

    if (cashPaymentChoice.equals("da", ignoreCase = true)) {
        // Solicităm suma disponibilă pentru plata în numerar
        println("Introduceti suma disponibila pentru plata in numerar:")
        val cashAvailableAmount = scanner.nextDouble()

        // Inițializăm plata în numerar
        val cashPayment = CashPayment(cashAvailableAmount)

        // Creăm un sistem de cumpărare de bilete utilizând plata în numerar
        val cashTicketPurchaseSystem = TicketPurchaseSystem(cashPayment)
        val cashPaymentResult = cashTicketPurchaseSystem.purchaseTicket(ticketPrice)

        // Afișăm rezultatul plății în numerar
        println("Rezultatul platii cu cardul: $cashPaymentResult")
    }
}