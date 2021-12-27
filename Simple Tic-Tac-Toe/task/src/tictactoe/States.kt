package tictactoe

const val ST_NOT_FINISHED = "Game not finished"
const val ST_IMPOSSIBLE = "Impossible"
const val ST_O_WINS = "O wins"
const val ST_X_WINS = "X wins"
const val ST_DRAW = "Draw"
const val X = 'X'
const val O = 'O'
const val X_WIN = "XXX"
const val O_WIN = "OOO"

enum class States(private val state: String) {
    NOT_FINISHED(ST_NOT_FINISHED),
    IMPOSSIBLE(ST_IMPOSSIBLE),
    DRAW(ST_DRAW),
    O_WINS(ST_O_WINS),
    X_WINS(ST_X_WINS);

    override fun toString(): String {
        return this.state
    }
}