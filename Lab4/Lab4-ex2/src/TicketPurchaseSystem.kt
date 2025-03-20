// Clasa TicketPurchaseSystem gestionează achiziția de bilete utilizând o metodă de plată specificată.
class TicketPurchaseSystem(private val paymentMethod: PaymentMethod) {
    // Metoda purchaseTicket încearcă să efectueze plata unui bilet cu prețul specificat.
    // Returnează true dacă plata a fost efectuată cu succes și false în caz contrar.
    fun purchaseTicket(ticketPrice: Double): Boolean {
        return paymentMethod.pay(ticketPrice) // Apelează metoda pay a obiectului PaymentMethod
    }
}