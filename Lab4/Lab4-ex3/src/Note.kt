import java.io.File

class Note(val title: String, val content: String) {
    // Funcția salvează notița într-un fișier text, cu titlul ca nume de fișier
    fun saveToFile() {
        File("notes/$title.txt").apply {
            parentFile?.mkdirs() // Creează directorul "notes" dacă nu există
            writeText(content) // Scrie conținutul notiței în fișier
        }
    }
}
