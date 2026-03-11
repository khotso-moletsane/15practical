public class Anagrams {

    // remove punctuation and convert to lowercase
    static String cleanWord(String w) {

        w = w.toLowerCase();           // convert word to lowercase
        w = w.replaceAll("[^a-z']", ""); // remove punctuation

        return w;
    }

}