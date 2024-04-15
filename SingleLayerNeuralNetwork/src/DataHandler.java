import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class DataHandler {
    HashMap<String, List<double[]>> languageVectors = new HashMap<>();
    public DataHandler(String trainingDataDirPath) throws IOException {
        File trainingDataDir = new File(trainingDataDirPath);

        for (File langDir : trainingDataDir.listFiles()){
            File[] files = langDir.listFiles();
            List<double[]> vectors = new ArrayList<>();
            for (File file : files){
                vectors.add(getCharsVector(Files.readString(file.toPath())));
            }
            languageVectors.put(langDir.getName(), vectors);
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
