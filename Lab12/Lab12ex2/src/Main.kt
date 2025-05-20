import java.io.File

// Funcție de criptare Caesar
fun caesarCipher(text: String, offset: Int): String {
    return text.map {
        if (it.isLetter()) {
            val base = if (it.isUpperCase()) 'A' else 'a'
            ((it - base + offset) % 26 + base.code).toChar()
        } else it
    }.joinToString("")
}

fun main() {
    val offset = 3
    val inputFile = "input.txt"

    // Citirea conținutului din fișier
    val content = File(inputFile).readText()

    // Prelucrarea fiecărui cuvânt
    val result = content.split(Regex("\\b"))
        .joinToString("") { word ->
            if (word.length in 4..7 && word.all { it.isLetter() })
                caesarCipher(word, offset)
            else word
        }

    println("Text criptat:\n$result")
}
