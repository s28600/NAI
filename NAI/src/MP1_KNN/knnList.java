package MP1_KNN;

import java.util.*;

public class knnList {
    String[] names;
    double[] distances;
    int index = 0;
    int pointer = 0;
    double maxDistance = 0;

    public knnList(int k) {
        names = new String[k];
        distances = new double[k];
    }

    void put(String name, double distance) {
        names[index] = name;
        distances[index] = distance;
        index++;
        calculateMax();
    }

    void replaceFurthest(String name, double distance){
        names[pointer] = name;
        distances[pointer] = distance;
        maxDistance = 0;
        calculateMax();
    }

    private void calculateMax(){
        for (int i = 0; i < distances.length; i++) {
            if (distances[i] > maxDistance){
                maxDistance = distances[i];
                pointer = i;
            }
        }
    }

    String getAnswer(){
        Set<String> answers = new HashSet<>(Arrays.asList(names));
        String answer = "";
        int counter = 0;
        for (String name : answers) {
            int n = Collections.frequency(Arrays.asList(names), name);
            if (n > counter){
                answer = name;
                counter = n;
            }
        }
        return answer;
    }
}
