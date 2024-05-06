import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<double[]> data;
        int k;
        try {
            data = Util.readFile(args[0]);
            k = Integer.parseInt(args[1]);
            if (k <= 0) {
                throw new RuntimeException("K should be greater than 0");
            }
        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException(e);
        }

        KMeans kMeans = new KMeans(data, k);
        kMeans.initializeCentroids();
    }
}
