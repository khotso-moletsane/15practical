import java.util.Arrays;

public class Anagrams {

    // remove punctuation and convert to lowercase
    static String cleanWord(String w) {

        w = w.toLowerCase();           // convert word to lowercase
        w = w.replaceAll("[^a-z']", ""); // remove punctuation

        return w;
    }

    // generate sorted signature for a word
    static String signature(String word) {

        char[] chars = word.toCharArray(); // convert word to char array

        Arrays.sort(chars);                // sort characters alphabetically

        return new String(chars);          // convert back to string
    }

}