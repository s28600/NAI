package MP2_Perceptron;

import java.util.Arrays;
import java.util.List;

public class Perceptron {
    double a;
    List<Item> items;
    double[] weights;

    public Perceptron(double a, DataSet trainSet) {
        this.a = a;
        this.items = trainSet.getItems();
        weights = new double[trainSet.getItems().getFirst().params.size()];

        for (int i = 0; i < weights.length; i++) {
            weights[i] = Math.random()*(trainSet.getMax()-trainSet.getMin())+trainSet.getMin();
        }
    }

    @Override
    public String toString() {
        return "Perceptron{" +
                "a=" + a +
                ", weights=" + Arrays.toString(weights) +
                '}';
    }
}
