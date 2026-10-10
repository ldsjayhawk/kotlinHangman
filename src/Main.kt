import kotlin.system.exitProcess

fun main() {
    gameMenu()
}

// game menu //
// read user input with selection variable
// use when statement to call the next function
fun gameMenu() {
    println("Welcome to Hangman!")
    println()
    println("1. Start Game")
    println("2. Instructions")
    println("3. Exit")

    val selection = readln().toInt()

    when (selection) {
        1 -> startGame()
        2 -> displayInstructions()
        3 -> exitProcess(0)
        else -> gameMenu()
    }
}

    // start game function //
    fun startGame(){
        // game variables //
        val words = setOf("hello","goodbye","stand","stormy","happy","phone")
        val secretWord = words.random() // secret word user is trying to guess
        var incorrectGuesses = 0 // number of incorrect guesses user has made
        var tryAgain = true // boolean to use to determine if game continues
        var userGuess: Char = ' ' // variable to hold user guess
        val incorrectLetters = mutableSetOf<Char>() // set to hold incorrect guesses
        val correctLetters = mutableSetOf<Char>()  // set to hold correct guesses
        val guessedLetters = mutableSetOf<Char>()  // set to hold all guesses
        var validGuess = false // variable to determine if user input is needed

        println("Shall we begin?")
         // contains user entry for guessed letter

        // while loop to display hint
        // replaces letters with _ before they have been guessed
        while(tryAgain) {
            var i = 0
            print("Word to guess: ")
            while (i < secretWord.length) {
                val letter = secretWord[i]
                if (letter in correctLetters) {
                    print(letter)
                } else {
                    print("_")
                }
                i++
            }
//            incorrectLetters.sort()

//            for (letter in incorrectLetters) {
//                print(letter)
//            }

            // sets validGuess to false to enter loop to get user guess
            validGuess = false

            // while loop gets users guess and
            // determines if the letter has already been guessed
            while (validGuess == false) {
                println()
                print("Guess a letter: ")
                userGuess = readln()[0] // retrieve letter from user
                userGuess = userGuess.lowercaseChar()

                // determine if letter has been guessed
                if (userGuess in guessedLetters) {
                    print("That letter has already been guessed.  Try again.")
                } else {
                    validGuess = true
                }
            }
            guessedLetters.add(userGuess)

            // conditional to determine if the guess is in the secret word
            // if it is, it will determine if the user has won
            if (userGuess in secretWord) {
                correctLetters.add(userGuess)
                if (secretWord.all{ letter -> letter in correctLetters}){
                    println()
                    println("$secretWord is correct!")
                    println("Congratulations! You win!")
                    println()
                    playAgain()
                }
            } else {
                // if the guess is not in the secret word
                // stor guess and determine if the game is over
                incorrectGuesses++
                incorrectLetters.add(userGuess)
                println()
                print("You have made $incorrectGuesses incorrect guesses.  You have ${6 - incorrectGuesses} incorrect guesses remaining.")
                println()
                // determine if guesses are remaining
                // if yes, set try again to allow to guess again
                // if no, display message and end game.  Offer user to play again
                if (incorrectGuesses == 6) {
                    println("You lose! The word is $secretWord.")
                    println()
                    tryAgain = false
                    playAgain()
                }
            }
        }
    }

// display instructions function //
// Instructions on how to play
fun displayInstructions(){
    println("HOW TO PLAY")
    println("Try to guess the word represented by the blanks shown.")
    println("Words are all common English words.")
    println("Blanks will be filed in as you make correct guesses.")
//    println("An incorrect guess will be displayed.")
    println("If you guess the word before making 6 incorrect guesses you win.")
    println("Press 'r' to return to main menu")

    var done = readln()

    done = done.lowercase()
    println(done)

    if (done == "r") {
        gameMenu()
    } else {
        displayInstructions()
    }
}

// play again function
// determines if user wants to play again and restarts or exits the game
fun playAgain() {
    print("Do you want to play again? y or n? ")
    val play: Char = readln()[0] // retrieve letter from user and save it in a variable

    play.lowercaseChar()

    when (play) {
        'y' -> gameMenu()
        else -> exitProcess(0)
    }
}

