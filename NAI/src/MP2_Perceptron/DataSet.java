package MP2_Perceptron;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataSet {
    private final List<Item> items = new ArrayList<>();
    private static final double[] range = new double[2];

    public DataSet(String filepath) throws IOException {
        Scanner scanner = new Scanner(Paths.get(filepath));
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
            items.add(new Item(params, line[line.length-1]));
        }
    }

    public List<Item> getItems(){return items;}
    public double getMin(){return range[0];}
    public double getMax(){return range[1];}
}
