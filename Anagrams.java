import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
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

    // print groups of words that share the same signature
    static void printAnagrams(HashMap<String, ArrayList<String>> dict) {

        for (String key : dict.keySet()) {

            ArrayList<String> words = dict.get(key);

            // only print groups with more than one word
            if (words.size() >= 2) {

                System.out.println(words);
            }
        }
    }

    // write anagrams to a LaTeX file
    static void writeAnagramsToLatex(HashMap<String, ArrayList<String>> dict, String filename) {

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {

            writer.println("\\documentclass{article}");
            writer.println("\\begin{document}");
            writer.println("\\begin{itemize}");

            for (String key : dict.keySet()) {

                ArrayList<String> words = dict.get(key);

                if (words.size() >= 2) {

                    String wordsList = String.join(", ", words);
                    writer.println("    \\item " + wordsList);
                }
            }

            writer.println("\\end{itemize}");
            writer.println("\\end{document}");

        } catch (IOException e) {

            System.out.println("Error writing to LaTeX file.");
        }
    }

    public static void main(String[] args) {

        HashMap<String, ArrayList<String>> dict = new HashMap<>();

        try {

            BufferedReader reader =
                    new BufferedReader(new FileReader("joyce1922_ulysses.text"));

            String line;

            while ((line = reader.readLine()) != null) {

                processLine(line, dict);
            }

            reader.close();

        } catch (IOException e) {

            System.out.println("Error reading file.");
        }

        printAnagrams(dict);
        writeAnagramsToLatex(dict, "anagrams_output.tex");
    }
}