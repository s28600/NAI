import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class Bayes {
    List<String[]> trainSet;
    HashMap<Integer, List<String>> availableAttributes;
    HashMap<String, Integer> decisiveAttributesCount;

    public Bayes(List<String[]> trainSet) {
        this.trainSet = trainSet;
        availableAttributes = new HashMap<>();
        decisiveAttributesCount = new HashMap<>();

        for (String[] line : trainSet){

            for (int i = 0; i < line.length-1; i++) {
                if (!availableAttributes.containsKey(i)){
                    availableAttributes.put(i, new ArrayList<>());
                }
                if (!availableAttributes.get(i).contains(line[i])){
                    availableAttributes.get(i).add(line[i]);
                }
            }

            if (!decisiveAttributesCount.containsKey(line[line.length-1]))
                decisiveAttributesCount.put(line[line.length-1], 0);
            decisiveAttributesCount.replace(line[line.length-1], decisiveAttributesCount.get(line[line.length-1])+1);
        }
    }

    public int conditionalProbability(String[] attributes, String condition){
        if (attributes.length != availableAttributes.size())
            throw new RuntimeException("Attributes count does not match available attributes");

        for (int i = 0; i < attributes.length; i++) {
            if (!availableAttributes.get(i).contains(attributes[i]))
                throw new RuntimeException("Attribute number " + (i+1) + " is not present in available attributes");
        }



        return 0;
    }
}
