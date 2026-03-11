import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;

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

    // add word to dictionary using its signature
    static void addWord(HashMap<String, ArrayList<String>> dict,
                        String key,
                        String word) {

        // if signature not yet in dictionary create new list
        if (!dict.containsKey(key)) {

            ArrayList<String> list = new ArrayList<>();

            list.add(word);

            dict.put(key, list);

        } else {

            // otherwise add word to existing list
            dict.get(key).add(word);
        }
    }

    // process each line from the file
    static void processLine(String line,
                            HashMap<String, ArrayList<String>> dict) {

        // split line into individual words
        String[] words = line.split("\\s+");

    }

}