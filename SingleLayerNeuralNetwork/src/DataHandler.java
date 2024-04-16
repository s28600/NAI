import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class DataHandler {
    List<LangVector> data = new ArrayList<>();
    String[] labels;

    public DataHandler(String trainingDataDirPath) throws IOException {
        File[] langDirs = new File(trainingDataDirPath).listFiles();
        List<String> labels = new ArrayList<>();

        for (File dir : langDirs){
            File[] files = dir.listFiles();
            labels.add(dir.getName());
            for (File file : files){
                data.add(new LangVector(dir.getName(), getCharsVector(Files.readString(file.toPath()))));
            }
        }

        this.labels = labels.toArray(new String[0]);
    }

    public static double[] getCharsVector(String input){
        String text = input.replaceAll("[^a-zA-Z]","").toUpperCase();
        if (text.isEmpty()) throw new RuntimeException("Input produced empty string, try another input data.");

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
