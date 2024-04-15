import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;

public class LanguageHandler {
    public static String readFileContent(String filepath){
        try {
            return Files.readString(Paths.get(filepath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static double[] getCharsVector(String input){
        String text = input.replaceAll("[^a-zA-Z]","").toUpperCase();

        LinkedHashMap<Character, Integer> charsCount = new LinkedHashMap<>();
        for (int i = 65; i <= 90; i++) {
            charsCount.put((char) i, 0);
        }

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            charsCount.put(ch, charsCount.get(ch)+1);
        }

        double[] vector = new double[26];
        int counter = 0;
        for (int n : charsCount.values()){
            vector[counter] = (double) n /text.length();
            counter++;
        }

        return vector;
    }
}
