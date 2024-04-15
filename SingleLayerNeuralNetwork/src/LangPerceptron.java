public class LangPerceptron extends Perceptron{
    String label;

    public LangPerceptron(double[] weights, double bias, String label) {
        super(weights, bias);
        this.label = label;
    }
}
