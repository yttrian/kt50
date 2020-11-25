package sudoku

import Problem

object Sudoku : Problem<Boolean> {
    private const val SUDOKU_SUM = 45

    private class Board<T>(private val backing: List<List<T>>) {
        fun pivot(): Board<T> = backing.mapIndexed { index, _ -> backing.map { it[index] } }.asBoard()

        fun either(predicate: (List<List<T>>) -> Boolean): Boolean =
            listOf(this.backing, this.pivot().backing).any(predicate)

        fun squares(): List<List<T>> = backing.chunked(SQUARE_SIZE).flatMap { rowGroup ->
            val chuckedRows = rowGroup.map { row -> row.chunked(SQUARE_SIZE) }
            chuckedRows.first().mapIndexed { index, _ -> chuckedRows.flatMap { chuckedRow -> chuckedRow[index] } }
        }

        companion object {
            private const val SQUARE_SIZE = 3
        }
    }

    override fun go(input: String): Boolean {
        val board = input.split("\n").map { it.split(" ").map(String::toInt) }.asBoard()

        return (board.either { it.any { line -> line.isValid() } } && board.squares().any { it.isValid() })
    }

    private fun <T> List<List<T>>.asBoard(): Board<T> = Board(this)
    private fun List<Int>.isValid(): Boolean = this.sum() == SUDOKU_SUM && this.isUnique()
    private fun List<Int>.isUnique(): Boolean = this.toSet().size == this.size
}
