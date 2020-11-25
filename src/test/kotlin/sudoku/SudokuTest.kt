package sudoku

import ResourcedTest
import kotlin.test.Test

internal class SudokuTest : ResourcedTest {
    @Test
    fun `must pass valid board`() {
        Sudoku passWithInput "sudoku-valid.txt"
    }

    @Test
    fun `must fail invalid board`() {
        Sudoku failWithInput "sudoku-invalid.txt"
    }
}
