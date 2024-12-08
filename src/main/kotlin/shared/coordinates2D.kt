package shared

data class Vector2D(val x: Int, val y: Int) {
    operator fun plus(other: Vector2D): Vector2D = Vector2D(x + other.x, y + other.y)
    operator fun minus(other: Vector2D): Vector2D = Vector2D(x - other.x, y - other.y)

    override fun toString(): String = "($x, $y)"
}

fun isVectorInGrid(vector2D: Vector2D, size: Pair<Int, Int>): Boolean {
    return (vector2D.x >= 0 && vector2D.x < size.first) && (vector2D.y >= 0 && vector2D.y < size.second)
}