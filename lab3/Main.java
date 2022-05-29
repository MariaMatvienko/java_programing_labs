package lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String rawValue = reader.readLine();

            TextProcessor textProcessor = new TextProcessorImpl();

            String inText = rawValue;
            System.out.printf("Processed text: %s\n", textProcessor.process(inText));

            System.out.printf("Template: %s\n", textProcessor.getTemplate());
            System.out.printf("Regex: %s\n", textProcessor.getRegex());
            System.out.printf("Examples: %s\n", Arrays.toString(textProcessor.get10Examples()));

            String inSecondText = rawValue;
            System.out.printf("Count of words matched with regex: %s\n", textProcessor.regexCount(inSecondText));
            System.out.printf("Words: %s\n", Arrays.toString(textProcessor.regexFind(inSecondText)));
            System.out.printf("Delete matched words: %s\n", textProcessor.regexRemove(inSecondText));
            System.out.printf("Replace matched words: %s\n", textProcessor.regexReplace(inSecondText));
        }
        catch (IOException e) {

        }
    }

}
