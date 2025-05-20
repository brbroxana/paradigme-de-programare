fun main() {
    val list = listOf(1, 21, 75, 39, 7, 2, 35, 3, 31, 7, 8)

    val result = list
        .filter { it >= 5 }
        .chunked(2) // grupează câte 2 elemente
        .map { it.reduce { acc, x -> acc * x } } // înmulțește perechile
        .sum() // adună rezultatele

    println("Rezultat final: $result") // Rezultat final: 2989
}
