import kotlin.system.exitProcess

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val secretWord = "Kotlin"
    var guesses = 0

    gameMenu()
}


fun gameMenu() {
    println("Welcome to Hangman!")
    println()
    println("1. Start Game")
    println("2. Instructions")
    println("3. Exit")

    val selection = readln().toInt()

    when (selection) {
//        1 -> startGame()
        2 -> displayHowTo()
        3 -> exitProcess(0)
        else -> gameMenu()
    }
}

//    fun startGame(){
//
//    }

fun displayHowTo(){
    println("HOW TO PLAY")
    println("You are trying to guess the word represented by the blanks shown.")
    println("You have 6 guesses to solve the word.  Blanks will be filed in as you make correct guesses.")
    println("An incorrect guess will be displayed.")
    println("If you guess the word within the requisite number of guesses you win.  Otherwise you lose.")
    println("Press 'r' to return to main menu")

    var done: String = ""
    done = readln()

    done = done.lowercase()
    println(done)

    if (done == "r") {
        gameMenu()
    } else {
        displayHowTo()
    }
}
