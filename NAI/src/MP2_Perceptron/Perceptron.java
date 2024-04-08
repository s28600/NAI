package MP2_Perceptron;

import java.util.Arrays;
import java.util.List;

public class Perceptron {
    double[] range;
    double a;
    List<Item> trainSet;
    double[] weights;

    public Perceptron(double[] range, double a, List<Item> trainSet) {
        this.range = range;
        this.a = a;
        this.trainSet = trainSet;
        weights = new double[trainSet.getFirst().params.size()];

        for (int i = 0; i < weights.length; i++) {
            weights[i] = Math.random()*range[1]*2-range[1];
        }
    }

    @Override
    public String toString() {
        return "Perceptron{" +
                "range=" + Arrays.toString(range) +
                ", a=" + a +
                ", weights=" + Arrays.toString(weights) +
                '}';
    }
}
