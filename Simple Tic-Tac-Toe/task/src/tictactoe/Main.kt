package tictactoe


fun main() {
    val field = Field()
    println(field)
    var mark = X
    while (true) {
        print("Enter the coordinates: ")
        val input = readLine()!!
        if (input.matches("\\d \\d".toRegex())) {
            val (r, c) = input.split(" ").map { it.toInt() }
            if (field.updateCell(r, c, mark)) {
                println(field)
                mark = if (mark == X) O else X
                if (field.checkStatus() == States.NOT_FINISHED) {
                    continue
                } else {
                    field.getStatus()
                    break
                }
            }
        } else {
            println("You should enter numbers!")
        }
    }
}