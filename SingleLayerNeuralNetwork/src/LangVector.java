import java.util.Arrays;

public class LangVector {
    String label;
    double[] vector;

    public LangVector(String label, double[] vector) {
        this.label = label;
        this.vector = vector;
    }

    @Override
    public String toString() {
        return label + ": " + Arrays.toString(vector);
    }
}
