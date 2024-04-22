import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Util {
    public static List<String[]> readFile(String path){
        List<String[]> out = new ArrayList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (scanner.hasNextLine()) {
            out.add(scanner.nextLine().split(","));
        }
        return out;
    }
}
