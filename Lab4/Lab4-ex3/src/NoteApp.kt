import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.layout.VBox
import javafx.stage.Stage

// Clasa principală a aplicației de notițe, extinde Application pentru a folosi JavaFX
class NoteApp : Application() {
    override fun start(primaryStage: Stage) {
        // Listă pentru afișarea titlurilor notițelor
        val noteList = ListView<String>().apply { items.addAll(NoteManager.listNotes()) } // Populează lista cu notițele existente
        // Zonă de text pentru afișarea și editarea conținutului notiței
        val contentArea = TextArea()

        // Buton pentru încărcarea notiței selectate
        val loadButton = Button("Load Note").apply {
            setOnAction {
                val selected = noteList.selectionModel.selectedItem
                // Încarcă conținutul notiței selectate sau afișează un mesaj de atenționare
                contentArea.text = selected?.let { NoteManager.loadNote(it) } ?: "Select a note!"
            }
        }

        // Buton pentru salvarea unei noi notițe
        val saveButton = Button("Save Note").apply {
            setOnAction {
                // Afișează un dialog pentru introducerea titlului
                val title = TextInputDialog("Enter title").showAndWait().orElse("")
                if (title.isNotBlank()) {
                    // Salvează notița și o adaugă în listă
                    NoteManager.saveNote(title, contentArea.text)
                    noteList.items.add(title)
                }
            }
        }
        // Buton pentru ștergerea notiței selectate
        val deleteButton = Button("Delete Note").apply {
            setOnAction {
                val selected = noteList.selectionModel.selectedItem
                if (selected != null && NoteManager.deleteNote(selected)) {
                    // Șterge notița și actualizează lista și zona de conținut
                    noteList.items.remove(selected)
                    contentArea.clear()
                }
            }
        }
        // Crearea layout-ului aplicației și adăugarea componentelor UI
        val layout = VBox(noteList, loadButton, saveButton, deleteButton, contentArea)
        // Configurarea scenei principale
        primaryStage.scene = Scene(layout, 400.0, 400.0)
        primaryStage.title = "Notite App"
        primaryStage.show()
    }
}
