package org.example

import org.w3c.dom.Document
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory
import org.xml.sax.InputSource

// Clasa XmlParser care implementează interfața Parser
// Aceasta este responsabilă de parsarea conținutului XML într-o structură Map
class XmlParser : Parser {
    // Suprascrie metoda parse pentru a transforma un șir XML într-o structură Map
    override fun parse(text: String): Map<String, Any> {
        val document = loadXml(text) // Încarcă și parsează XML-ul într-un document DOM
        return parseElement(document.documentElement) // Procesează elementul rădăcină
    }
    // Încarcă XML-ul primit ca text și îl transformă într-un obiect Document
    private fun loadXml(xml: String): Document {
        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder()
        return builder.parse(InputSource(StringReader(xml))) // Parsează XML-ul folosind un flux de citire
    }
    // Parcurge elementele XML și le adaugă într-o hartă (Map)
    private fun parseElement(element: org.w3c.dom.Element): Map<String, Any> {
        val map = mutableMapOf<String, Any>()
        val nodeList = element.childNodes
        // Iterăm prin toate nodurile copil și adăugăm elementele în hartă
        for (i in 0 until nodeList.length) {
            val node = nodeList.item(i)
            if (node is org.w3c.dom.Element) {
                map[node.tagName] = node.textContent.trim() // Salvăm conținutul elementului
            }
        }
        return map // Returnăm structura Map rezultată
    }
}
