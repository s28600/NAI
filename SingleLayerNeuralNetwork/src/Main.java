import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(Arrays.toString(LanguageHandler.getCharsVector("TrainingData/FR/Bleach (BLEACH, ブリーチ, Burīchi) est .txt")));
    }
}
