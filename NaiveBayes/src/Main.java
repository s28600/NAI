public class Main {
    public static void main(String[] args) {
        Bayes bayes = new Bayes(Util.readFile("data/trainingset.csv"));
        System.out.println(bayes.availableAttributes);
        System.out.println(bayes.decisiveAttributesCount);
        System.out.println(bayes.conditionalProbability(new String[]{"deszcz","tak","niska","niska"}, "tak"));
    }
}
