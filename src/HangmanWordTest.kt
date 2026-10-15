import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class HangmanWordTest {
    val gameWord = HangmanWord("stormy")

    @Test
    fun `check letter is in word is true`(){
        val result = gameWord.guessLetter('o')
        assertTrue(result)
    }

    @Test
    fun `check incorrect letter is in word is false`(){
        val result = gameWord.guessLetter('x')
        assertFalse(result)
    }

    @Test
    fun `check non letter is invalid`(){
        val result = gameWord.guessLetter('*')
        assertFalse(result)
    }

    @Test
    fun `check if word is guessed is false`(){
        val gameWord = HangmanWord("phrase")
        gameWord.guessLetter('p')
        val result = gameWord.isWordGuessed()
        assertFalse(result)
    }

    @Test
    fun `check if word is guessed is true`(){
        val gameWord = HangmanWord("phrase")
        gameWord.guessLetter('p')
        gameWord.guessLetter('h')
        gameWord.guessLetter('r')
        gameWord.guessLetter('a')
        gameWord.guessLetter('s')
        gameWord.guessLetter('e')
        val result = gameWord.isWordGuessed()
        assertTrue(result)
    }

    @Test
    fun `check duplicate guesses are not stored`(){
        val gameWord = HangmanWord("shout")
        gameWord.guessLetter('s')
        gameWord.guessLetter('s')
        val size = gameWord.correctLetters.size
        assertEquals(1, size)
    }


}