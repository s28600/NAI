package MP2_Perceptron;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class DataSet {
    final List<Item> entries = new ArrayList<>();
    final double[] range = new double[2];
    int entryParamsNumber;
    final LinkedHashSet<String> labels = new LinkedHashSet<>();

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
            labels.add(line[line.length-1]);
            entries.add(new Item(params, line[line.length-1]));
        }
        entryParamsNumber = entries.getFirst().params.size();
        Collections.shuffle(entries);
    }

    public double getMin(){return range[0];}
    public double getMax(){return range[1];}
}
