/**
 * Created by owner on 1/31/2017.
 */
import org.junit.*;
import sun.plugin2.message.Message;

import java.util.*;
import java.io.*;

//This class tests the methods of HangmanManager
public class HangmanManagerTest {
    //Private method initializes dictionary set from given file
    private Set<String> constructorTestHelper() {
        try {
            Scanner file = new Scanner(new File("dictionary.txt"));
            Set<String> words = new TreeSet<>();
            while (file.hasNext()) {
                words.add(file.next());
            }
            return words;
        } catch (FileNotFoundException e) {
            Assert.fail("File cannot be read");
        }
        return null;
    }

    //This method tests if the given parameters are appropriate for the constructor
    @Test
    public void ConstructorTest() {
        Set<String> dictionary = constructorTestHelper();
        new HangmanManager(dictionary, 4, 10);
        new HangmanManager(dictionary, 3, 20);
        new HangmanManager(dictionary, 6, 100);
        new HangmanManager(dictionary, 10, 50);
    }

    //This method tests if the words method returns the copy of wordSet
    @Test
    public void wordsTest() {
        Set<String> dictionary = constructorTestHelper();
        HangmanManager game1 = new HangmanManager(dictionary, 8, 5);
        //this means they are two different copies with different memory addresses
        Assert.assertNotEquals(game1.words(), dictionary);
    }

    //This method tests if the guessesLeft decrements if user guesses wrong answer
    @Test
    public void guessesLeftTest() {
        Set<String> dictionary = constructorTestHelper();
        HangmanManager game1 = new HangmanManager(dictionary, 4, 2);
        Assert.assertEquals(game1.guessesLeft(), 2);
        //This would decrement the number of guesses user has left
        game1.record('a');
        game1.record('z');
        Assert.assertEquals(game1.guessesLeft(), 0);
    }

    //This method tests if the guesses method returns user's guesses correctly
    @Test
    public void guessesTest() {
        Set<String> dictionary = constructorTestHelper();
        SortedSet<Character> guessList = new TreeSet<>();
        HangmanManager game1 = new HangmanManager(dictionary, 3, 3);
        guessList.add('a');
        Assert.assertNotEquals(game1.guesses(), guessList);
        guessList.add('z');
        guessList.add('u');
        Assert.assertNotEquals(game1.guesses(), guessList);
    }

    //This method tests if the pattern changes with given length and user's guess
    @Test
    public void patternTest() {
        Set<String> dictionary = constructorTestHelper();
        HangmanManager game1 = new HangmanManager(dictionary, 4, 10);
        Assert.assertEquals(game1.pattern(), "- - - -");
        //if record returns 0, means it is wrong guess
        Assert.assertTrue(game1.record('a') == 0);
        //pattern should then be the same
        Assert.assertEquals(game1.pattern(), "- - - -");
    }

    @Test
    public void recordTest() {
        Set<String> words = new TreeSet<>();
        Set<String> dictionary = constructorTestHelper();
        HangmanManager game1 = new HangmanManager(dictionary, 4, 10);
        for (String someWord : dictionary) {
            if (someWord.length() == 4) {
                words.add(someWord);
            }
        }
        Assert.assertEquals(game1.words(), words);
    }
}

