import java.util.List;

public class Item {
    List<Double> params;
    String label;

    public Item(List<Double> params, String label) {
        this.params = params;
        this.label = label;
    }

    @Override
    public String toString() {
        return "Item{" +
                "params=" + params +
                ", name='" + label + '\'' +
                '}';
    }
}
