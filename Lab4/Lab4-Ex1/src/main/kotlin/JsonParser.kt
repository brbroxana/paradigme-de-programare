package org.example

import khttp.responses.Response
import org.json.JSONObject
// Clasa JsonParser care implementează interfața Parser
// Aceasta este responsabilă de parsarea conținutului JSON într-o hartă (Map)
class JsonParser : Parser {
    // Suprascrie metoda parse pentru a transforma un șir JSON într-o structură Map
    override fun parse(text: String): Map<String, Any> {
        val jsonObject = JSONObject(text) // Crearea unui obiect JSON din textul primit
        val map = jsonObject.toMap() // Conversia obiectului JSON într-un Map
        return map // Returnează rezultatul parsării
    }
}