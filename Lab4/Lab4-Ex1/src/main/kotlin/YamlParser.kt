package org.example

import org.yaml.snakeyaml.Yaml
// Clasa YamlParser care implementează interfața Parser
// Aceasta este responsabilă de parsarea conținutului YAML într-o structură Map
class YamlParser : Parser {
    // Suprascrie metoda parse pentru a transforma un șir YAML într-o structură Map
    override fun parse(text: String): Map<String, Any> {
        val yaml = Yaml() // Creează o instanță a parserului YAML
        return yaml.load(text) as Map<String, Any> // Convertește conținutul YAML într-un Map și îl returnează
    }
}