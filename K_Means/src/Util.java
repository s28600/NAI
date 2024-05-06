import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Util {
    public static ArrayList<double[]> readFile(String filepath) throws IOException {
        ArrayList<double[]> out = new ArrayList<>();
        Scanner scanner = new Scanner(Path.of(filepath));
        String[] line;
        while (scanner.hasNextLine()){
            line = scanner.nextLine().split(",");
            out.add(Arrays.stream(line).mapToDouble(Double::valueOf).toArray());
        }
        return out;
    }
}
