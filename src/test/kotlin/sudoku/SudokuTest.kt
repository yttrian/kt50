package sudoku

import ResourcedTest
import kotlin.test.Test

internal class SudokuTest : ResourcedTest {
    @Test
    fun `must pass valid board`() {
        Sudoku withInput "sudoku-valid.txt"
    }
}
