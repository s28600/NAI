package MP2_Perceptron;

import java.util.HashMap;
import java.util.Map;

public class Trainer {
    double a;
    DataSet trainSet;
    DataSet testSet;

    public Trainer(double a, DataSet trainSet, DataSet testSet) {
        this.a = a;
        this.trainSet = trainSet;
        this.testSet = testSet;
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
            int d = item.label.equals(trainSet.labels.getFirst())?1:0;
            if (y != d) {
                p.learn(item.params, d, y, a);
            }
        }
    }

    public void testPerceptron(Perceptron p){
        System.out.println("Target class: " + trainSet.labels.getFirst());
        HashMap<String, double[]> accuracy = new HashMap<>();

        for (Item item : testSet.entries){
            if (!accuracy.containsKey(item.label)) {
                accuracy.put(item.label, new double[2]);
            }
            accuracy.get(item.label)[0]++;
            int y = p.compute(item.params);
            int d = item.label.equals(trainSet.labels.getFirst())?1:0;
            if (y == d) {
                accuracy.get(item.label)[1]++;
            }
            System.out.println("Entry label: " + item.label + "\t" + (y==d?"PASS":"FAIL"));
        }

        for (String key : accuracy.keySet()){
            double ac = accuracy.get(key)[1]/accuracy.get(key)[0]*100;
            System.out.println("Accuracy for class " + key + ": " + ac + "%");
        }
    }
}
