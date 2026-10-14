import kotlin.system.exitProcess
import java.io.File

fun main() {
    gameMenu()
}

// game menu //
// displays game menu
// uses user response to call the requested function
fun gameMenu() {
    println("Welcome to Hangman!")
    println()
    println("1. Start Game")
    println("2. Instructions")
    println("3. Exit")

    val selection = readln().toIntOrNull()

    when (selection) {
        1 -> startGame()
        2 -> displayInstructions()
        3 -> exitProcess(0)
        else -> {
            println("Invalid selection.  Please select 1, 2 or 3.")
            gameMenu()
        }
    }
}

class HangmanWord (val secretWord: String) {
    val correctLetters = mutableSetOf<Char>()  // set to hold correct guesses

    // function checks if letter is in the secret word
    fun guessLetter(letter: Char):Boolean {
        if (letter in secretWord) {
            correctLetters.add(letter)
            return true
        }
        return false
    }

    // function checks if secret word has been guessed
    fun isWordGuessed(): Boolean{
        return secretWord.all { letter -> letter in correctLetters }
    }
}

    // start game function //
    // contains main game functionality
    fun startGame(){
        // game variables //
        val listOfWords = readFile("Words.txt")
        val secretWord = listOfWords.random() // secret word user is trying to guess
        val gameWord = HangmanWord(secretWord)
        var incorrectGuesses = 0 // number of incorrect guesses user has made
        var tryAgain = true // boolean to use to determine if game continues
        var userGuess = ' ' // variable to hold user guess
        val incorrectLetters = mutableSetOf<Char>() // set to hold incorrect guesses
        val guessedLetters = mutableSetOf<Char>()  // set to hold all guesses
        var validGuess = false // variable to determine if user input is needed

        println("Shall we begin?")
         // contains user entry for guessed letter

        // while loop to display hint
        // replaces letters with _ before they have been guessed
        while(tryAgain) {
            var i = 0
            print("Letters that have been guessed: ")
            for (x in guessedLetters) {
                print(x)
                print(" ")
            }
            println()

            print("Word to guess: ")
            while (i < secretWord.length) {
                val letter = secretWord[i]
                if (letter in gameWord.correctLetters) {
                    print(letter)
                } else {
                    print("_")
                }
                i++
            }

            // sets validGuess = false to enter loop to get user guess
            validGuess = false

            // while loop gets users guess and
            // determines if the letter has already been guessed
            while (validGuess == false) {
                println()
                print("Guess a letter: ")
                userGuess = readln()[0] // retrieve letter from user
                userGuess = userGuess.lowercaseChar()
                if (userGuess in guessedLetters) {
                    print("$userGuess has already been guessed.  Try another letter.")
                    println()
                } else if (!userGuess.isLetter()){
                    print("$userGuess is not a letter.  Please try again.")
                    println()
                } else {
                    guessedLetters.add(userGuess)
                    validGuess = true
                }
            }

            // conditional to determine if the guess is in the secret word
            // if it is, it will determine if the user has won
            if (gameWord.guessLetter(userGuess)) {
                if (gameWord.isWordGuessed()){
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
                println("The letter '$userGuess' is not in the word.  ${6 - incorrectGuesses} incorrect guesses remaining.")
            }

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

// function to read file containing words to be used in the game
// stores words in a set for use in the game
fun readFile(fileName: String): MutableSet<String>{
    val words = mutableSetOf<String>()  // set to hold all guesses
    File(fileName).forEachLine { line -> words.add(line.trim()) }
    return words
}

// displays instructions on how to play
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

// determines if user wants to play again and restarts or exits the game
fun playAgain() {
    print("Do you want to play again? y or n? ")
    var play: Char = readln()[0] // retrieve letter from user and save it in a variable

    play = play.lowercaseChar()

    when (play) {
        'y' -> gameMenu()
        else -> exitProcess(0)
    }
}

