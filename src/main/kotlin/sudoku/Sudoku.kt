package sudoku

import Problem

object Sudoku : Problem<Boolean> {
    private const val SUDOKU_SUM = 45

    private class Board<T>(backing: List<List<T>>) : List<List<T>> by backing {
        val pivot: Board<T> by lazy { this.mapIndexed { index, _ -> this.map { it[index] } }.asBoard() }

        val squares: List<List<T>> by lazy {
            this.chunked(SQUARE_SIZE).flatMap { rowGroup ->
                val chuckedRows = rowGroup.map { row -> row.chunked(SQUARE_SIZE) }
                (0 until SQUARE_SIZE).map { index -> chuckedRows.flatMap { chuckedRow -> chuckedRow[index] } }
            }
        }

        fun either(predicate: (List<List<T>>) -> Boolean): Boolean = listOf(this, this.pivot).any(predicate)

        companion object {
            private const val SQUARE_SIZE = 3
        }
    }

    override fun go(input: String): Boolean {
        val board = input.split("\n").map { it.split(" ").map(String::toInt) }.asBoard()

        return (board.either { it.any { line -> line.isValid() } } && board.squares.any { it.isValid() })
    }

    private fun <T> List<List<T>>.asBoard(): Board<T> = Board(this)
    private fun List<Int>.isValid(): Boolean = this.sum() == SUDOKU_SUM && this.isUnique()
    private fun List<Int>.isUnique(): Boolean = this.toSet().size == this.size
}
