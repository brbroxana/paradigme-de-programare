// Clasa User reprezintă un utilizator care poate crea și gestiona notițe
class User(val name: String) {

    // Listă privată pentru stocarea notițelor utilizatorului
    private val notes = mutableListOf<Note>()

    // Funcție pentru crearea unei noi notițe
    fun createNote(title: String, content: String) {
        val note = Note(title, content) // Creează o nouă notiță
        notes.add(note) // Adaugă notița în lista utilizatorului
        note.saveToFile() // Salvează notița într-un fișier
    }

    // Funcție care returnează o listă cu titlurile tuturor notițelor utilizatorului
    fun getNotes(): List<String> = notes.map { it.title }
}
