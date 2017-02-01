/**
 * Created by owner on 1/31/2017.
 */
import org.junit.*;
import sun.plugin2.message.Message;

import java.util.*;
import java.io.*;

//This class tests the methods of HangmanManager
public class HangmanManagerTest {
   private Set<String> constructorTestHelper() {
       try {
           Scanner file = new Scanner(new File("dictionary.txt"));
           Set<String> words = new TreeSet<>();
           while (file.hasNext()) {
               words.add(file.next());
           }
           return words;
       }
       catch (FileNotFoundException e) {
           Assert.fail("File cannot be read");
       }
       return null;
   }

    @Test void ConstructorTest() {
        Set<String> dictionary = constructorTestHelper();
        new HangmanManager(dictionary,4,10);
        new HangmanManager(dictionary,3,20);
        new HangmanManager(dictionary,6,100);
        new HangmanManager(dictionary,0,50);
    }

    @Test void wordsTest() {

    }
}
