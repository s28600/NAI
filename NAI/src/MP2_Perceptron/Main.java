package MP2_Perceptron;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static double[] range = new double[2];
    public static void main(String[] args) throws IOException {
        double a = Double.parseDouble(args[0]);
        String trainSetPath = args[1];
        String testSetPath = args[2];

        List<Item> trainSet = createDataSet(trainSetPath);
        List<Item> testSet = createDataSet(testSetPath);

        Perceptron p = new Perceptron(range, a ,trainSet);
        System.out.println(p);
    }

    static List<Item> createDataSet(String filepath) throws IOException {
        Scanner scanner = new Scanner(Paths.get(filepath));
        List<Item> dataSet = new ArrayList<>();
        List<Double> params;
        String[] line;
        while (scanner.hasNextLine()){
            line = scanner.nextLine().split(";");
            params = new ArrayList<>();
            for (int i = 0; i < line.length-1; i++) {
                double param = Double.parseDouble(line[i]);
                params.add(param);
                if (param < range[0]) range[0] = param;
                if (param > range[1]) range[1] = param;
            }
            dataSet.add(new Item(params, line[line.length-1]));
        }
        return dataSet;
    }
}
