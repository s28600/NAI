package MP2_Perceptron;

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
            if (y!=1 || d!= 1) {
                p.learn(item.params, d, y, a);
            }
        }
    }
}
