package lab3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;

public class TextProcessorImpl implements TextProcessor {

    private final String wordsSplitter = " ";

    private final String regex = "^\\S*[a-z][a-z]\\d$";

    @Override
    public String process(String inputSequence) {

        String[] words = inputSequence.split(wordsSplitter);
        List<String> wordsPrepared = new ArrayList<>();
        int sequenceN = Integer.parseInt(words[0]);

        for (int i = 1; i < words.length; i++) {
            if(i % sequenceN == 0) {
                wordsPrepared.add(words[i]);
            }
        }

        return String.join(wordsSplitter, wordsPrepared);
    }

    @Override
    public int regexCount(String inputSequence) {

        AtomicInteger count = new AtomicInteger();

        doWithMatchesWords(inputSequence, word -> count.getAndIncrement());

        return count.get();
    }

    @Override
    public String regexRemove(String inputSequence) {
        return String.join(wordsSplitter, proceedWithWords(inputSequence, word -> ""));
    }

    @Override
    public String regexReplace(String inputSequence) {
        return String.join(wordsSplitter, proceedWithWords(inputSequence, word -> "*****"));
    }

    @Override
    public String[] regexFind(String inputSequence) {

        List<String> words = new ArrayList<>();

        doWithMatchesWords(inputSequence, words::add);

        return words.toArray(new String[0]);
    }

    @Override
    public String getTemplate() {
        return "\\*[αα]#";
    }

    @Override
    public String getRegex() {
        return regex;
    }

    @Override
    public String[] get10Examples() {
        return new String[]{
                "no1",
                "favour7",
                "HEight3",
                "Faa9",
                "random3",
                "update4",
                "steward4",
                "SUNRIse4",
                "butterfly4",
                "ideology7"
        };
    }

    private void doWithMatchesWords(String inputSequence, Consumer<String> action) {
        proceedWithWords(
                inputSequence,
                word -> {
                    action.accept(word);
                    return "";
                });
    }

    private String[] proceedWithWords(String inputSequence, Function<String, String> action) {
        List<String> words = new ArrayList<>();

        List.of(inputSequence.split(wordsSplitter)).forEach(word -> {
            if (word.matches(regex)) {
                words.add(action.apply(word));
            } else {
                words.add(word);
            }
        });

        return words.toArray(new String[0]);
    }

}
