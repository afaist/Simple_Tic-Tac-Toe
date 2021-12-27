package tictactoe

import java.lang.Integer.max
import kotlin.math.absoluteValue

const val HEAD_TOP = "---------"
const val PIPE = "|"

class Field {
    private val field = Array(3) { CharArray(3) { ' ' } }

    /**
     * MOVE
     *
     * @param row
     * @param column
     * @return
     */
    fun updateCell(row: Int, column: Int, mark: Char): Boolean {
        if (row !in 1..3 || column !in 1..3) {
            println("Coordinates should be from 1 to 3!")
            return false
        }
        val i = row - 1
        val j = column - 1
        return if (field[i][j] == ' ') {
            field[i][j] = mark
            true
        } else {
            println("This cell is occupied! Choose another one!")
            false
        }

    }

    /**
     * Print status of field
     *
     */
    fun getStatus() {
        val status = checkStatus()
        println(status)
    }

    /**
     * Check status field
     *
     * @return
     */
    fun checkStatus(): States {
        val xCount = countMarks(X)
        val oCount = countMarks(O)

        if ((xCount - oCount).absoluteValue > 1) {
            return States.IMPOSSIBLE
        }
        if (max(xCount, oCount) < 3) {
            return States.NOT_FINISHED
        }
        val stat = getFill()
        var xWin = false
        var oWin = false
        for (row in stat) {
            if (row == X_WIN) {
                if (xWin) {
                    return States.IMPOSSIBLE
                }
                xWin = true
            } else if (row == O_WIN) {
                if (oWin) {
                    return States.IMPOSSIBLE
                }
                oWin = true
            }
        }
        if (oWin && xWin) {
            return States.IMPOSSIBLE
        }
        if (oWin) {
            return States.O_WINS
        }
        if (xWin) {
            return States.X_WINS
        }
        if (xCount + oCount == 9) {
            return States.DRAW
        }
        return States.NOT_FINISHED
    }

    /**
     * Count cells with mark
     *
     * @param mark
     * @return
     */
    private fun countMarks(mark: Char): Int {
        var n = 0
        for (row in field) {
            for (char in row) {
                if (char == mark) {
                    n++
                }
            }
        }
        return n
    }

    /**
     * Fill rows, columns and diagonals
     *
     * @return
     */
    private fun getFill(): Array<String> {
        val fill = Array(8) { "" }
        // check rows
        val str = StringBuilder()
        for ((i, row) in field.withIndex()) {
            str.clear()
            for (c in row) {
                str.append(c)
            }
            fill[i] = str.toString()
        }
        // check columns and diagonal
        val columns = Array(5) { CharArray(3) { ' ' } }
        for ((i, row) in field.withIndex()) {
            for ((j, char) in row.withIndex()) {
                columns[j][i] = char
                if (i == j) {
                    columns[3][i] = char
                }
                if (i + j == 2) {
                    columns[4][2 - i] = char
                }
            }
        }
        for ((i, row) in columns.withIndex()) {
            fill[i + 3] = row.joinToString("")
        }
        return fill
    }

    /**
     * Field to String
     *
     * @return
     */
    override fun toString(): String {
        val string = StringBuilder()
        string.append("$HEAD_TOP\n")
        for (row in field) {
            string.append("$PIPE ")
            for (char in row) {
                string.append(char)
                string.append(" ")
            }
            string.append("$PIPE\n")
        }
        string.append(HEAD_TOP)
        return string.toString()
    }
}