import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GrammarExercise {

    public static void main(String[] args) {
        //需要从命令行读入
        String firstWordList = args[1];
        String secondWordList = args[2];

        List<String> result = findCommonWordsWithSpace(firstWordList, secondWordList);
        //按要求输出到命令行
        result.forEach(System.out::println);

    }

    public static List<String> findCommonWordsWithSpace(String firstWordList,
        String secondWordList) {
        //在这编写实现代码
        List<String> firstWordArrayList = Arrays.asList(firstWordList.split(","));
        List<String> secondWordArrayList = Arrays.asList(secondWordList.split(","));

        List<String> first = firstWordArrayList.stream().map(word -> {
            if (checkWord(word)) {
                return word;
            } else {
                throw new RuntimeException();
            }
        }).distinct().sorted(String.CASE_INSENSITIVE_ORDER).collect(Collectors.toList());
        List<String> second = secondWordArrayList.stream().map(word -> {
            if (checkWord(word)) {
                return word;
            } else {
                throw new RuntimeException();
            }
        }).distinct().sorted(String.CASE_INSENSITIVE_ORDER).collect(Collectors.toList());

        return findCommonHelper(first, second);
    }

    /**
     * Return True if this is a word.
     */
    private static boolean checkWord(String word) {
        if (word.length() == 0) {
            return false;
        }

        List<Character> chars = word.chars()
            .mapToObj(e -> (char) e)
            .filter(c -> (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
            .collect(Collectors.toList());

        return word.length() == chars.size();
    }

    private static List<String> findCommonHelper(List<String> list1, List<String> list2) {
        return list1.stream()
            .filter(word1 -> list2.stream().anyMatch(word1::equalsIgnoreCase))
            .collect(Collectors.toList());
    }

}
