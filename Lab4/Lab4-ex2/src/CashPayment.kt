// Clasa care implementează metoda de plată în numerar
class CashPayment(private var availableAmount: Double) : PaymentMethod {
    // Suprascrierea metodei 'pay' din interfața PaymentMethod
    override fun pay(fee: Double) : Boolean {
        // Verificăm dacă suma disponibilă este suficientă pentru plată
        return if (availableAmount >= fee) {
            availableAmount -= fee // Scădem suma plătită din soldul disponibil
            true // Plata a fost efectuată cu succes
        } else {
            false // Fonduri insuficiente, plata eșuează
        }
    }
}