package org.example

import khttp.responses.Response
import org.json.JSONObject
// Clasa Crawler care primește un URL și un parser pentru procesarea conținutului
class Crawler(private val url: String, private val parser: Parser) {
    // Metodă pentru obținerea resursei de la URL printr-o cerere HTTP GET
    fun getResource(): Response {
        return khttp.get(url)
    }

    // Metodă pentru selectarea parserului corespunzător pe baza tipului de conținut primit
    private fun getParserforContent(contentType: String): Parser {
        return when (contentType) {
            "application/json" -> JsonParser() // Parser pentru JSON
            "text/html" -> object : Parser {  // tratează HTML ca text simplu
                override fun parse(text: String): Map<String, Any> {
                    // Returnăm HTML ca text simplu, fără a-l parsează
                    return mapOf("html_content" to text)
                }
            }
            else -> throw UnsupportedOperationException("Formatul $contentType nu este susținut.")
        }
    }

    // Metodă pentru procesarea conținutului, selectând automat parserul adecvat
    fun processContent(content: String, contentType: String): Map<String, Any> {
        // Obținem parserul corespunzător tipului de conținut
        val parserForContent = getParserforContent(contentType)
        return parserForContent.parse(content)
    }
}
