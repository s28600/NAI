package MP2_Perceptron;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Trainer {
    double a;
    DataSet trainSet;
    DataSet testSet;
    String targetClassName;

    public Trainer(double a, DataSet trainSet, DataSet testSet) {
        this.a = a;
        this.trainSet = trainSet;
        this.testSet = testSet;
        targetClassName = trainSet.labels.getFirst();
    }

    public Perceptron createPerceptron() {
        double[] weights = new double[trainSet.entryParamsNumber];
        for (int i = 0; i < weights.length; i++) {
            weights[i] = Math.random()*(trainSet.getMax()-trainSet.getMin())+trainSet.getMin();
        }
        double bias = Math.random()*(trainSet.getMax()-trainSet.getMin())+trainSet.getMin();

        return new Perceptron(weights, bias);
    }

    public void trainPerceptron(Perceptron p){
        for (Item item : trainSet.entries){
            int y = p.compute(item.params);
            int d = item.label.equals(targetClassName)?1:0;
            if (y != d) p.learn(item.params, d, y, a);
        }
    }

    public void testPerceptron(Perceptron p){
        System.out.println("Target class: " + targetClassName);
        HashMap<String, double[]> accuracy = new HashMap<>();

        for (Item item : testSet.entries){
            if (!accuracy.containsKey(item.label)) {
                accuracy.put(item.label, new double[2]);
            }
            accuracy.get(item.label)[0]++;
            int y = p.compute(item.params);
            int d = item.label.equals(targetClassName)?1:0;
            if (y == d) {
                accuracy.get(item.label)[1]++;
            }
            System.out.println("Entry class: " + item.label + "\t" + (y==d?"PASS":"FAIL"));
        }

        List<Double> accuracies = new ArrayList<>();
        double ac = 0;
        for (String key : accuracy.keySet()){
            ac = accuracy.get(key)[1]/accuracy.get(key)[0]*100;
            accuracies.add(ac);
            System.out.println("Accuracy for class " + key + ": " + ac + "%");
        }
        ac = 0;
        for (double n : accuracies){
            ac+=n;
        }
        ac /= accuracies.size();
        System.out.println("Overall accuracy: " + ac + "%");
    }
}
