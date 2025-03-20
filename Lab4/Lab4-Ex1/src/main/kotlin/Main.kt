package org.example

import khttp.responses.Response
import org.json.JSONObject
// Interfață pentru parser, definind metoda parse
interface Parser {
    fun parse(text: String): Map<String, Any>
}

fun main() {
    val url = "http://mike.tuiasi.ro/" // Schimbă cu URL-ul real

    try {
        val jsonParser = JsonParser() // Creează un parser implicit (ex. JSON)
        val crawler = Crawler(url, jsonParser) // Passează parserul la constructor

        val response = crawler.getResource()

        // Extragem tipul de conținut din antetul 'Content-Type'
        val contentType = response.headers["Content-Type"] ?: throw Exception("Tipul de conținut nu a fost găsit")

        // Procesăm conținutul cu tipul de conținut extras
        val parsedData = crawler.processContent(response.text, contentType)

        println("Rezultatul parsării: $parsedData")
    } catch (e: Exception) {
        println("Eroare: ${e.message}")
    }
}


