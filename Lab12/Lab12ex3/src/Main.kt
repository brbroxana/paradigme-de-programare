import java.io.File
import kotlin.math.sqrt

data class Point(val x: Int, val y: Int)

fun distance(p1: Point, p2: Point): Double {
    val dx = p1.x - p2.x
    val dy = p1.y - p2.y
    return sqrt((dx * dx + dy * dy).toDouble())
}

fun main() {
    val inputFile = File("puncte.txt")
    val lines = inputFile.readLines()

    val n = lines.first().toInt()
    val points = lines.drop(1).take(n).map { line ->
        val (x, y) = line.trim().split(" ").map { it.toInt() }
        Point(x, y)
    }

    val distances = points.zipWithNext { a, b -> distance(a, b) }
    val perimeter = distances.sum() + distance(points.last(), points.first())

    println("Perimetrul este: $perimeter")
}
