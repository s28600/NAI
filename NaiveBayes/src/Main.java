public class Main {
    public static void main(String[] args) {
        Bayes bayes = new Bayes(Util.readFile("data/trainingset.csv"));
        UI ui = new UI(bayes);
        ui.start();
    }
}
