import java.util.*;

public class Layer {
    double a;
    DataHandler dataHandler;
    List<LangPerceptron> perceptrons = new ArrayList<>();
    private static final double requiredAccuracy = 0.95;
    private static boolean debug = false;

    public Layer(double a, DataHandler dataHandler) {
        this.dataHandler = dataHandler;
        this.a = a;

        for (String label : dataHandler.labels){
            double[] weights = new double[dataHandler.data.getFirst().vector.length];
            for (int i = 0; i < weights.length; i++) {
                weights[i] = Math.random();
            }
            perceptrons.add(new LangPerceptron(weights, Math.random(), label));
        }

        if (debug){
            System.out.print("Layer created:");
            System.out.println(this);
        }
    }

    public static void setDebug(boolean val){
        debug = val;
    }

    public void train(){
        int iterations;
        double accuracy;
        double hits;

        if (debug) System.out.println("\nTraining perceptrons:");

        for (LangPerceptron perceptron : perceptrons){
            iterations = 0;
            do {
                hits = 0;
                for (LangVector langVector : dataHandler.data){
                    int y = (int) perceptron.compute(langVector.vector);
                    int d = langVector.label.equals(perceptron.label)?1:0;
                    perceptron.learn(langVector.vector, d, y, a);
                    if (y==d) hits++;
                }
                accuracy = hits/dataHandler.data.size();
                iterations++;
            } while (accuracy < requiredAccuracy);

            if (debug) {
                System.out.println("Trained:");
                System.out.println(perceptron.label + " " + perceptron);
                System.out.println("Accuracy: " + accuracy);
                System.out.println("Iterations completed: " + iterations);
            }
        }
    }

    public String compute(String text){
        int maxIndex = 0;
        double max = -1;
        double[] results = new double[perceptrons.size()];

        if (debug) System.out.println();
        for (int i = 0; i < perceptrons.size(); i++) {
            results[i] = perceptrons.get(i).net(DataHandler.getCharsVector(text));
            if (debug) {
                System.out.println("Perceptron " + perceptrons.get(i).label + " activation: " + results[i]);
            }
        }

        System.out.println(Arrays.toString(results));

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
