import java.io.File

// Obiectul NoteManager gestionează notițele: salvare, încărcare, ștergere și listare
object NoteManager {
    // Directorul unde sunt salvate notițele, se creează automat dacă nu există
    private val notesDir = File("notes").apply { mkdirs() }

    // Funcție pentru salvarea unei notițe într-un fișier text
    fun saveNote(title: String, content: String) {
        File(notesDir, "$title.txt").writeText(content)
    }

    // Funcție pentru încărcarea conținutului unei notițe
    fun loadNote(title: String): String? {
        val file = File(notesDir, "$title.txt")
        // Verifică dacă fișierul există și returnează conținutul, altfel returnează null
        return if (file.exists()) file.readText() else null
    }

    // Funcție pentru ștergerea unei notițe; returnează true dacă ștergerea a avut succes
    fun deleteNote(title: String): Boolean {
        return File(notesDir, "$title.txt").delete()
    }

    // Funcție care returnează o listă cu titlurile tuturor notițelor salvate
    fun listNotes(): List<String> {
        return notesDir.listFiles()?.map { it.nameWithoutExtension } ?: emptyList()
    }
}
