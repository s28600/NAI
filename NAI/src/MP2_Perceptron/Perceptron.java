package MP2_Perceptron;

import java.util.Arrays;
import java.util.List;

public class Perceptron {
    double a;
    List<Item> items;
    double[] weights;
    double bias;
    String targetClassName;

    public Perceptron(double a, DataSet trainSet, String targetClassName) {
        this.a = a;
        this.items = trainSet.getItems();
        this.targetClassName = targetClassName;
        weights = new double[trainSet.getItems().getFirst().params.size()];

        for (int i = 0; i < weights.length; i++) {
            weights[i] = Math.random()*(trainSet.getMax()-trainSet.getMin())+trainSet.getMin();
        }
        bias = Math.random()*(trainSet.getMax()-trainSet.getMin())+trainSet.getMin();
        System.out.println("Created perceptron:" +
                "\n\tTarget class: " + targetClassName +
                "\n\tLearning rate: " + a +
                "\n\tWeights: " + Arrays.toString(weights) +
                "\n\tBias: " + bias);
    }

    public int compute(List<Double> params){
        double net = 0;
        for (int i = 0; i < params.size(); i++) {
            net += params.get(i)*weights[i];
        }
        net -= bias;
        return net>=0?1:0;
    }

    public void correct(List<Double> params, int y){
        for (int i = 0; i < params.size(); i++) {
            weights[i] += (1-y)*a* params.get(i);
        }
        bias -= (1-y)*a;
    }

    @Override
    public String toString() {
        return "Perceptron{" +
                "a=" + a +
                ", weights=" + Arrays.toString(weights) +
                ", bias=" + bias +
                '}';
    }
}
