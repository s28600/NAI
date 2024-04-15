import java.util.*;

public class Layer {
    double a;
    DataHandler dataHandler;
    List<LangPerceptron> perceptrons = new ArrayList<>();

    public Layer(double a, DataHandler dataHandler) {
        this.dataHandler = dataHandler;

        for (String label : dataHandler.labels){
            double[] weights = new double[dataHandler.data.getFirst().vector.length];
            for (int i = 0; i < weights.length; i++) {
                weights[i] = Math.random();
            }
            perceptrons.add(new LangPerceptron(weights, Math.random(), label));
        }
        System.out.println(this);

        for (int i = 0; i < 5; i++) {
            for (LangPerceptron perceptron : perceptrons){
                for (LangVector langVector : dataHandler.data){
                    int y = (int) perceptron.compute(langVector.vector);
                    int d = langVector.label.equals(perceptron.label)?1:0;
                    if (y!=d) perceptron.learn(langVector.vector, d, y, a);
                }
            }
        }
        System.out.println(this);
    }

    public String compute(String text){
        int maxIndex = 0;
        double max = -1;
        double[] results = new double[perceptrons.size()];

        for (int i = 0; i < perceptrons.size(); i++) {
            results[i] = perceptrons.get(i).net(DataHandler.getCharsVector(text));
        }

        System.out.println("\n" + Arrays.toString(results));

        for (int i = 0; i < results.length; i++) {
            if (results[i] > max) {
                max = results[i];
                maxIndex = i;
            }
        }

        return dataHandler.labels[maxIndex];
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (LangPerceptron p : perceptrons){
            out.append("\n").append(p.label).append(" ").append(p);
        }
        return out.toString();
    }
}
