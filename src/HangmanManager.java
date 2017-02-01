import java.util.*;

//This class manages the Evil Hangman Game. It takes the user's choice of word length and guesses
//to start the game.
//It uses one of the dictionary text files and uses input to choose which word will be the answer.
public class HangmanManager {

    //Holds current dictionary of words
    private Set<String> wordSet;
    //Holds list of guessed letters right or wrong
    private SortedSet<Character> guessedLetters;
    //Maps patterns to word families
    private Map<String, Set<String>> wordMap;
    //Number of guesses user can have
    private int numOfGuesses;
    //Chosen pattern
    private String wordPattern;

    //This method initializes setting of game with dictionary set, length of word, and number of guesses
    public HangmanManager(Collection<String> dictionary, int length, int max) {
        //If the word in wordSet does not match length then do not add to wordSet
        if (length < 1 || max < 0) {
            throw new IllegalArgumentException();
        }

        wordSet = new TreeSet<>();
        guessedLetters = new TreeSet<>();

        numOfGuesses = max;

        for (String word : dictionary) {
            if (word.length() == length) {
                wordSet.add(word);
            }
        }

        //Initial state pattern
        wordPattern = "";
        for (int i = 1; i <= length; i++) {
            wordPattern += "-";
            if (i != length) {
                wordPattern += " ";
            }
        }
    }

    //Method returns the set of words available
    public Set<String> words() {
        return new TreeSet<>(wordSet);
    }

    //Method returns how many guesses player has left
    public int guessesLeft() {
        return numOfGuesses;
    }

    //Method returns current set of letters guessed by user
    public SortedSet<Character> guesses() {
        return new TreeSet<>(guessedLetters);

    }

    //Method returns current pattern
    public String pattern() {
        if (wordSet.isEmpty()) {
            throw new IllegalStateException();
        }
        return wordPattern;
    }

    //Method records the users guess and changes the current set of words and pattern
    public int record(char guess) {
        int count = 0;
        if (numOfGuesses < 1 || wordSet.isEmpty()) {
            throw new IllegalStateException();
        }
        if (!wordSet.isEmpty() && guessedLetters.contains(guess)) {
            throw new IllegalArgumentException();
        }
        //adds guess to list of guessed Letters
        guessedLetters.add(guess);

        Map<String, TreeSet<String>> wordFamilyMap = buildMap(guess);
        wordSet = choosePattern(wordFamilyMap, wordSet, guess);
        for (int i = 0; i < wordPattern.length(); i++) {
            if (wordPattern.charAt(i) == guess) {
                count++;
            }
        }
        if (count == 0) {
            numOfGuesses--;
        }

        return count;
    }

    //Helper method builds a map mapping the patterns to their respective word families
    private Map<String, TreeSet<String>> buildMap(char guess) {
        // String newPattern = "";
        Map<String, TreeSet<String>> wordFamilyMap = new TreeMap<>();
        for (String word : wordSet) {
            String key = getKey(word, guess);
            if (wordFamilyMap.containsKey(key)) {
                TreeSet<String> wordSet = wordFamilyMap.get(key);
                wordSet.add(word);
            } else {
                TreeSet<String> newWordSet = new TreeSet<>();
                newWordSet.add(word);
                wordFamilyMap.put(key, newWordSet);
            }
        }
        return wordFamilyMap;
    }

    //This private method creates a pattern for given word
    private String getKey(String word, char guess) {
        StringBuilder sb = new StringBuilder(wordPattern);
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                //word is found so will put guessed letter at found index
                sb.setCharAt(i * 2, guess);
            }
        }
        return sb.toString();
    }

    //This helper method chooses the pattern that will be current by comparing its set size to others
    private Set<String> choosePattern(Map<String, TreeSet<String>> wordFamilyMap, Set<String> wordSet, char guess) {
        int setSize = -1;
        for (String key : wordFamilyMap.keySet()) {
            //check size of values
            if (wordFamilyMap.get(key).size() > setSize) { //is biggest set
                setSize = wordFamilyMap.get(key).size();
                wordPattern = key;
                wordSet = wordFamilyMap.get(key);
            }
        }
        return wordSet;
    }
}