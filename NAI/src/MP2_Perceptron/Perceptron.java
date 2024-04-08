package MP2_Perceptron;

import java.util.Arrays;
import java.util.List;

public class Perceptron {
    double[] weights;
    double bias;

    public Perceptron(double[] weights, double bias) {
        this.weights = weights;
        this.bias = bias;

        System.out.println("Created perceptron:" +
                "\n\tWeights: " + Arrays.toString(weights) +
                "\n\tBias: " + bias);
    }

    public int compute(List<Double> params){
        double net = -bias;
        for (int i = 0; i < params.size(); i++) {
            net += params.get(i)*weights[i];
        }
        return net>=0?1:0;
    }

    public void learn(List<Double> params, int d, int y, double a){
        for (int i = 0; i < params.size(); i++) {
            weights[i] += (d-y)*a*params.get(i);
        }
        bias -= (1-y)*a;
    }

    @Override
    public String toString() {
        return "Perceptron:" +
                "\n\tWeights: " + Arrays.toString(weights) +
                "\n\tBias: " + bias;
    }
}
