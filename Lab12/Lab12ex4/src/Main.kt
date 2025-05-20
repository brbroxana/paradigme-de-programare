// Functor pentru MutableMap
class MapFunctor<K, V>(val map: MutableMap<K, V>) {
    fun map(transform: (V) -> V): MapFunctor<K, V> {
        val newMap = map.mapValues { (_, v) -> transform(v) }.toMutableMap()
        return MapFunctor(newMap)
    }

    fun get(): MutableMap<K, V> = map
}

// Funcție extensie pe String -> PascalCase
fun String.toPascalCase(): String =
    this.split(" ").joinToString("") { it.lowercase().replaceFirstChar { ch -> ch.uppercaseChar() } }

fun main() {
    // Map de test
    val testMap = mutableMapOf(
        1 to "lorem ipsum",
        2 to "dolor sit amet",
        3 to "hello world"
    )

    // Transformări cu functor
    val result = MapFunctor(testMap)
        .map { "Test $it" }
        .map { it.toPascalCase() }
        .get()

    // Afișare rezultat
    println("Map transformat:")
    result.forEach { (k, v) -> println("$k -> $v") }
}
