import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        if (!Files.exists(Paths.get("TrainingData"))){
            System.out.println("No training data was found - exiting.");
            return;
        }

        DataHandler dataHandler = new DataHandler("TrainingData");
        Layer.setDebug(true);
        Layer layer = new Layer(0.5, dataHandler);
        layer.train();

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter something and pray, God rest your soul:" +
                "\n(Type \"Amen\" to quit)" +
                "\n-> ");
        String input = scanner.nextLine();
        while (!input.equals("Amen")){
            System.out.println("Result: " + layer.compute(input) + "\n");
            System.out.print("\nEnter something and pray, God rest your soul:" +
                    "\n(Type \"Amen\" to quit)" +
                    "\n-> ");
            input = scanner.nextLine();
        }
    }
}
