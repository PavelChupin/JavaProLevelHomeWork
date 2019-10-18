package homework.lesson3;

import java.util.*;

public class Words {
    List<String> words;

    Words(String[] words) {
        this.words = new ArrayList<String>();
        this.words.addAll(Arrays.asList(words));
    }

    public Set<String> uniq() {
        Set<String> strings = new HashSet<String>();
        strings.addAll(this.words);
        return strings;
    }


    public Map<String, Integer> countWords() {
        Map<String, Integer> counts = new HashMap<String, Integer>();
        for (String s : uniq()) {
            int count = 0;
            for (String w : words) {
                if (w.equals(s)) {
                    count++;
                }
            }
            counts.put(s, count);
            //Другие варианты 1- вый параметр это значение из Map по ключу, второй параметр на сколько увеличить первый, третий какую функцию можно использовать.
            //counts.merge(s,1,(oldValue, newValue)-> oldValue + newValue);
            //counts.merge(s,1,Integer::sum);

        }
        return counts;
    }

    public Map<String, Integer> countWords(int a) {
        Map<String, Integer> counts = new HashMap<String, Integer>();
        for (String s : uniq()) {
            for (String w : words) {
                if (w.equals(s)) {
                    //1- вый параметр это значение из Map по ключу, второй параметр на сколько увеличить первый, третий какую функцию можно использовать.
                    counts.merge(s, 1, Integer::sum);
                }
            }
        }
        return counts;
    }

    public Map<String, Integer> countWords(int a, int b) {
        Map<String, Integer> counts = new HashMap<>();
        for (String s : uniq()) {
            for (String w : words) {
                if (w.equals(s)) {
                    //1- вый параметр это значение из Map по ключу, второй параметр на сколько увеличить первый, третий какую функцию можно использовать.
                    counts.merge(s, 1, (oldValue, newValue) -> oldValue + newValue);
                }
            }
        }
        return counts;
    }

}
