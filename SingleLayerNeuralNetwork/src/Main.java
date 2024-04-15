import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        if (!Files.exists(Paths.get("TrainingData"))){
            System.out.println("No training data was found - exiting.");
            return;
        }

        DataHandler dataHandler = new DataHandler("TrainingData");
        System.out.println(dataHandler.languageVectors);

        //System.out.println(Arrays.toString(LanguageHandler.getCharsVector("TrainingData/FR/Bleach (BLEACH, ブリーチ, Burīchi) est .txt")));
    }
}
