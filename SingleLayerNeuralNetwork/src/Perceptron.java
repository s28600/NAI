import java.util.Arrays;

public class Perceptron {
    double[] weights;
    double bias;

    public Perceptron(double[] weights, double bias) {
        this.weights = weights;
        this.bias = bias;
    }

    public double net(double[] params){
        if (params.length != weights.length){
            throw new RuntimeException("Input vector has different length than " +
                    "perceptron weights vector - unable to compute.");
        }
        double net = -bias;
        for (int i = 0; i < params.length; i++) {
            net += params[i]*weights[i];
        }
        return net;
    }

    public double compute(double[] params){
        return net(params)>=0?1:0;
    }

    public void learn(double[] params, int d, int y, double a){
        if (params.length != weights.length){
            throw new RuntimeException("Input vector has different length than " +
                    "perceptron weights vector - unable to learn.");
        }
        for (int i = 0; i < params.length; i++) {
            weights[i] += (d-y)*a*params[i];
        }
        bias -= (d-y)*a;
    }

    @Override
    public String toString() {
        return "Perceptron:" +
                "\n\tWeights: " + Arrays.toString(weights) +
                "\n\tBias: " + bias;
    }
}
