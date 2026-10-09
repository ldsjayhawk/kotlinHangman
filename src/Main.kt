import kotlin.system.exitProcess

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
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
        1 -> startGame()
        2 -> displayHowTo()
        3 -> exitProcess(0)
        else -> gameMenu()
    }
}

    fun startGame(){
        println("Shall we begin?")
        val secretWord = "Kotlin" // secret word user is trying to guess
        var incorrectGuesses = 0 // number of incorrect guesses user has made
        var tryAgain = true // boolean to use to determine if game continues
//        var userWord = ""
//        var character: String = "" // initialize character as string
        var userGuess = "" // contains user entry for guessed letter
        val incorrectLetters = mutableListOf<String>() //contains user guessed letters that are not in the secret word

        // while loop to repeat guesses until win or loss
        while(tryAgain) {
            // display the secret word with _ when the letter has not been guessed
            print(secretWord)
            incorrectLetters.sort()
            for (letter in incorrectLetters) {
                print(letter)

                // get the user's guess
                userGuess += readln()[0] // retrieve letter from user and save it in a variable

                // compare the current "word" with the secret word
                // conditional based on if word matches
                // if the guess does match, then declare the winner
                if (userGuess == secretWord) { // compare userGuess to secretWord
                    println("You win!") // display win message
                    tryAgain = false // set try again variable to false so game terminates
                    gameMenu() // send user back to menu
                    // if the guess doesn't match
                    //check # of incorrect guesses
                    // add letter to incorrect guess array
                } else {
                    incorrectGuesses++ // increase number of incorrect guesses by 1
                    incorrectLetters.add(userGuess)
                    print("You have made $incorrectGuesses guesses.  You have ${incorrectGuesses - 1} guesses remaining.")
                    // determine if guesses are remaining
                    // if yes, return to next guess
                    // if no, display message and end game
                    if (incorrectGuesses == 6) {
                        println("You lose!")
                        tryAgain = false
                        gameMenu()
                    }
                }
            }
        }
    }

fun displayHowTo(){
    println("HOW TO PLAY")
    println("You are trying to guess the word represented by the blanks shown.")
    println("You have 6 guesses to solve the word.  Blanks will be filed in as you make correct guesses.")
    println("An incorrect guess will be displayed.")
    println("If you guess the word within the requisite number of guesses you win.  Otherwise you lose.")
    println("Press 'r' to return to main menu")

    var done = readln()

    done = done.lowercase()
    println(done)

    if (done == "r") {
        gameMenu()
    } else {
        displayHowTo()
    }
}
