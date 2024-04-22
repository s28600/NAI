public class Main {
    public static void main(String[] args) {
        Bayes bayes = new Bayes(Util.readFile("data/trainingset.csv"));
        String[] attributes = {"snieg","tak","wysoka","srednia"};
        System.out.println(bayes.conditionalProbability(attributes, "tak"));
        System.out.println(bayes.conditionalProbability(attributes, "nie"));
        System.out.println(bayes.getVerdict(attributes));
        System.out.println();
        for (String[] line : bayes.trainSet){
            String expected = line[line.length-1];
            String actual = bayes.getVerdict(new String[]{line[0],line[1],line[2],line[3]});
            System.out.println("Expected: " + expected + ", actual: " + actual + " " + (!expected.equals(actual)?"FAIL":""));
        }
    }
}
