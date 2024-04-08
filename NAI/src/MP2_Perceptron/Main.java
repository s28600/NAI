package MP2_Perceptron;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Trainer trainer = new Trainer(Double.parseDouble(args[0]), new DataSet(args[1]), new DataSet(args[2]));
        UI.start(trainer);
    }
}
