import java.util.*;

public class Bayes {
    static boolean DEBUG = false;
    List<String[]> trainSet;
    HashMap<Integer, List<String>> availableAttributes;
    HashMap<String, Integer> decisiveAttributesCount;

    public Bayes(List<String[]> trainSet) {
        this.trainSet = trainSet;
        availableAttributes = new HashMap<>();
        decisiveAttributesCount = new HashMap<>();

        for (String[] line : trainSet){
            //Collecting available attributes
            for (int i = 0; i < line.length-1; i++) {
                if (!availableAttributes.containsKey(i)){
                    availableAttributes.put(i, new ArrayList<>());
                }
                if (!availableAttributes.get(i).contains(line[i])){
                    availableAttributes.get(i).add(line[i]);
                }
            }

            //Collecting decisive attributes
            if (!decisiveAttributesCount.containsKey(line[line.length-1]))
                decisiveAttributesCount.put(line[line.length-1], 0);
            decisiveAttributesCount.replace(line[line.length-1], decisiveAttributesCount.get(line[line.length-1])+1);
        }

        if (DEBUG){
            System.out.println("Training data initiated.");
            System.out.println("Available attributes by index:");
            for (Integer key : availableAttributes.keySet()){
                System.out.println("\t" + key + " = " + availableAttributes.get(key));
            }
            System.out.println("Available decisive attributes: ");
            for (String key : decisiveAttributesCount.keySet()){
                System.out.println("\t" + key);
            }
        }
    }

    public double conditionalProbability(String[] attributes, String condition){
        //User error checking
        if (attributes.length != availableAttributes.size())
            throw new RuntimeException("Attributes count does not match available attributes");
        else if (!decisiveAttributesCount.containsKey(condition))
            throw new RuntimeException("Condition not present in available decisive attributes");
        for (int i = 0; i < attributes.length; i++) {
            if (!availableAttributes.get(i).contains(attributes[i]))
                throw new RuntimeException("Attribute number " + (i+1) + " is not present in available attributes");
        }

        int[] attributesCount = new int[attributes.length];
        for (String[] line : trainSet){
            if (!line[line.length-1].equals(condition)) continue;

            for (int i = 0; i < line.length-1; i++) {
                if (attributes[i].equals(line[i])) attributesCount[i]++;
            }
        }

        double probability = 1;
        for (int i = 0; i < attributesCount.length; i++) {
            if (attributesCount[i] == 0){
                probability *= (double) (attributesCount[i]+1) / (decisiveAttributesCount.get(condition)+availableAttributes.get(i).size());
            } else {
                probability *= (double) attributesCount[i] / decisiveAttributesCount.get(condition);
            }
        }
        probability *= (double) decisiveAttributesCount.get(condition)/trainSet.size();

        if (DEBUG) System.out.println("Count for condition \"" + condition + "\": " + Arrays.toString(attributesCount));

        return probability;
    }

    public String getVerdict(String[] attributes){
        if (DEBUG) System.out.println("Attributes delivered: " + Arrays.toString(attributes));

        String[] verdicts = decisiveAttributesCount.keySet().toArray(new String[0]);
        double[] verdictsProbabilities = new double[decisiveAttributesCount.size()];
        for (int i = 0; i < verdicts.length; i++) {
            verdictsProbabilities[i] = conditionalProbability(attributes, verdicts[i]);
        }
        int index = 0;
        double max = 0;
        for (int i = 0; i < verdictsProbabilities.length; i++) {
            if (verdictsProbabilities[i] > max){
                max = verdictsProbabilities[i];
                index = i;
            }
        }
        return verdicts[index];
    }
}
