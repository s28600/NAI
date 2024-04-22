public class Main {
    public static void main(String[] args) {
        Bayes.DEBUG = true;
        Bayes bayes = new Bayes(Util.readFile("data/trainingset.csv"));
        String[] attributes = {"snieg","tak","wysoka","srednia"};
        System.out.println(bayes.getVerdict(attributes));
        System.out.println();
        bayes.checkTrainSet();
        bayes.checkTestSet(Util.readFile("data/testset.csv"));
    }
}
