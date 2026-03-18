import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Anagrams {

    // remove punctuation and convert to lowercase
    static String cleanWord(String w) {

        w = w.toLowerCase();           // convert word to lowercase
        w = w.replaceAll("[^a-z']", ""); // remove punctuation

        return w;
    }

    // generate sorted signature for a word
    static String signature(String word) {

        char[] chars = word.toCharArray();

        Arrays.sort(chars);

        return new String(chars);

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

        // go through each word
        for (String w : words) {

            w = cleanWord(w); // remove punctuation

            // ignore very short words
            if (w.length() > 1) {

                String key = signature(w); // create signature

                addWord(dict, key, w); // store word
            }
        }
    }

    // print groups of words that share the same signature (no duplicates)
    static void printAnagrams(HashMap<String, ArrayList<String>> dict) {

        for (String key : dict.keySet()) {

            ArrayList<String> words = dict.get(key);

            // remove duplicates using a HashSet
            HashSet<String> uniqueWords = new HashSet<>(words);

            // only print groups with more than one unique word
            if (uniqueWords.size() >= 2) {

                System.out.println(uniqueWords);
            }
        }
    }

    // write anagrams to a LaTeX file (just the \item lines, no preamble)
    static void writeAnagramsToLatex(HashMap<String, ArrayList<String>> dict, String filename) {

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {

            for (String key : dict.keySet()) {

                ArrayList<String> words = dict.get(key);

                // Remove duplicates
                HashSet<String> uniqueWords = new HashSet<>(words);

                if (uniqueWords.size() >= 2) {

                    String wordsList = String.join(", ", uniqueWords);
                    writer.println("    \\item " + wordsList);
                }
            }

        } catch (IOException e) {

            System.out.println("Error writing to LaTeX file.");
        }
    }

    // write all unique words to a text file (one per line) for this week's practical
    static void writeWordsToFile(HashMap<String, ArrayList<String>> dict, String filename) {

        // Use a TreeSet to automatically sort the words alphabetically
        TreeSet<String> allWords = new TreeSet<>();

        for (ArrayList<String> wordList : dict.values()) {
            allWords.addAll(wordList);
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {

            for (String word : allWords) {
                writer.println(word);
            }
            System.out.println("Wrote " + allWords.size() + " unique words to " + filename);

        } catch (IOException e) {
            System.out.println("Error writing to file: " + filename);
        }
    }

    public static void main(String[] args) {

        HashMap<String, ArrayList<String>> dict = new HashMap<>();

        try {

            BufferedReader reader =
                    new BufferedReader(new FileReader("joyce1922_ulysses-1.text"));

            String line;

            while ((line = reader.readLine()) != null) {

                processLine(line, dict);
            }

            reader.close();

        } catch (IOException e) {

            System.out.println("Error reading file.");
            return; // exit if file not found
        }

        // Print anagram groups to console (no duplicates)
        System.out.println("=== Anagram groups (unique words) ===");
        printAnagrams(dict);

        // Write LaTeX content file (just the \item lines)
        writeAnagramsToLatex(dict, "theAnagrams.tex");
        System.out.println("\nLaTeX content written to theAnagrams.tex");

        // Write all unique words to a text file for this week's heapsort practical
        writeWordsToFile(dict, "cleaned_words.txt");
    }
}