package MP2_Perceptron;

import java.util.List;

public class Item {
    List<Double> params;
    String name;

    public Item(List<Double> params, String name) {
        this.params = params;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Item{" +
                "params=" + params +
                ", name='" + name + '\'' +
                '}';
    }
}
