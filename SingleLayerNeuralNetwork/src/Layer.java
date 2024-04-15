import java.util.*;

public class Layer {
    double a;
    LinkedHashMap<String, Perceptron> perceptrons = new LinkedHashMap<>();
    DataHandler dataHandler;

    public Layer(double a, DataHandler dataHandler) {
        this.dataHandler = dataHandler;
        for (String label : dataHandler.labels){
            double[] weights = new double[dataHandler.data.getFirst().vector.length];
            for (int i = 0; i < weights.length; i++) {
                weights[i] = Math.random();
            }
            perceptrons.put(label, new Perceptron(weights, Math.random()));
        }
        System.out.println(this);

        for (Map.Entry<String, Perceptron> entry : perceptrons.entrySet()){
            for (LangVector langVector : dataHandler.data){
                int y = (int) entry.getValue().compute(langVector.vector);
                int d = langVector.label.equals(entry.getKey())?1:0;
                entry.getValue().learn(langVector.vector, d, y, a);
            }
        }
        System.out.println(this);
    }

    public String compute(String text){
        int maxIndex = 0;
        double max = -1;
        List<Double> out = new ArrayList<>();

        for (Map.Entry<String, Perceptron> entry : perceptrons.entrySet()){
            out.add(entry.getValue().net(DataHandler.getCharsVector(text)));
        }

        for (int i = 0; i < out.size(); i++) {
            if (out.get(i) > max) {
                max = out.get(i);
                maxIndex = i;
            }
        }

        return dataHandler.labels[maxIndex];
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (Map.Entry<String, Perceptron> entry : perceptrons.entrySet()){
            out.append("\n").append(entry.getKey()).append(" ").append(entry.getValue());
        }
        return out.toString();
    }
}
