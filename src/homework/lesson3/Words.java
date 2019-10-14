package homework.lesson3;

import java.util.*;

public class Words {
    List<String> words;

    Words(String[] words) {
        this.words = new ArrayList<>();
        this.words.addAll(Arrays.asList(words));
    }

    public Set<String> uniq() {
        Set<String> strings = new HashSet<>();
        strings.addAll(this.words);
        return strings;
    }

    public Map<String, Integer> countWords() {
        Map<String, Integer> counts = new HashMap<>();
        for (String s : uniq()) {
            int count = 0;
            for (String w : words) {
                if (w.equals(s)) {
                    count++;
                }
            }
            counts.put(s, count);
        }
        return counts;
    }

}
