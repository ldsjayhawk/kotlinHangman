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
        val words = setOf("hello","goodbye","stand","stormy","happy","phone")
        val secretWord = words.random() // secret word user is trying to guess
        var incorrectGuesses = 0 // number of incorrect guesses user has made
        var tryAgain = true // boolean to use to determine if game continues
        var userGuess: Char = ' '
        val incorrectLetters = mutableSetOf<Char>()
        val correctLetters = mutableSetOf<Char>()
        val guessedLetters = mutableSetOf<Char>()
        var validGuess = false

         // contains user entry for guessed letter

        // while loop to repeat guesses until win or loss
            // display the secret word with _ when the letter has not been guessed

        while(tryAgain) {
3
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
            validGuess = false

            while (validGuess == false) {
                println()
                print("Guess a letter: ")
                userGuess = readln()[0] // retrieve letter from user and save it in a variable
                userGuess = userGuess.lowercaseChar()

                if (userGuess in guessedLetters) {
                    print("That letter has already been guessed.  Try again.")
                } else {
                    validGuess = true
                }
            }
            guessedLetters.add(userGuess)

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
                incorrectGuesses++ // increase number of incorrect guesses by 1
                incorrectLetters.add(userGuess)
                println()
                print("You have made $incorrectGuesses incorrect guesses.  You have ${6 - incorrectGuesses} incorrect guesses remaining.")
                println()
                // determine if guesses are remaining
                // if yes, return to next guess
                // if no, display message and end game
                if (incorrectGuesses == 6) {
                    println("You lose! The word is $secretWord.")
                    println()
                    tryAgain = false
                    playAgain()
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

fun playAgain() {
    print("Do you want to play again? y or n? ")
    val play: Char = readln()[0] // retrieve letter from user and save it in a variable

    play.lowercaseChar()
    when (play) {
        'y' -> gameMenu()
        else -> exitProcess(0)
    }
}

