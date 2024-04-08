package MP2_Perceptron;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        double a = Double.parseDouble(args[0]);
        String trainSetPath = args[1];
        String testSetPath = args[2];

        DataSet trainSet = new DataSet(trainSetPath);
        Perceptron p = new Perceptron(a, trainSet, trainSet.getItems().getFirst().name);

        DataSet testSet = new DataSet(testSetPath);

    }
}
