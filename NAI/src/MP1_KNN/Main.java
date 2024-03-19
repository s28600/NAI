package MP1_KNN;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int k = Integer.parseInt(args[0]);
        String trainSetPath = args[1];
        String testSetPath = args[2];

        List<Item> trainSet = createDataSet(trainSetPath);
        List<Item> testSet = createDataSet(testSetPath);

        long start = System.currentTimeMillis();
        checkTestData(k, trainSet, testSet);
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("Time: " + timeElapsed + "ms");
        checkForInput(k, trainSet);
    }

    static List<Item> createDataSet(String filepath) throws IOException {
        Scanner scanner = new Scanner(Paths.get(filepath));
        List<Item> dataSet = new ArrayList<>();
        List<Double> params;
        String[] line;
        while (scanner.hasNextLine()){
            line = scanner.nextLine().split(";");
            params = new ArrayList<>();
            for (int i = 0; i < line.length-1; i++) {
                params.add(Double.parseDouble(line[i]));
            }
            dataSet.add(new Item(params, line[line.length-1]));
        }
        return dataSet;
    }

    static String kNN(int k, Item item, List<Item> trainSet) {
        knnList knnList = new knnList(k);

        for (int i = 0; i < trainSet.size(); i++) {
            double distance = distance(item.params, trainSet.get(i).params);
            if (i < k){
                knnList.put(trainSet.get(i).name, distance);
            } else if (distance < knnList.maxDistance) {
                knnList.replaceFurthest(trainSet.get(i).name, distance);
            }
        }

        return knnList.getAnswer();
    }

    static void checkTestData(int k, List<Item> trainSet, List<Item> testSet) {
        String answer;
        int hits = 0;

        for (Item testItem : testSet){
            answer = kNN(k, testItem, trainSet);
            if (testItem.name.equals(answer)) hits++;
            System.out.println("Expected: " + testItem.name + ", got: " + answer);
        }

        System.out.println("Accuracy: " + (double)hits/(double)testSet.size()*100 + "%");
    }

    static void checkForInput(int k, List<Item> trainSet){
        int paramsNumber = trainSet.get(0).params.size();
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to run kNN for input parameters?(yes/no): ");
        String input = scanner.next();

        while (input.equals("yes")){
            System.out.print("Enter " + paramsNumber +  " parameters: ");
            List<Double> params = new ArrayList<>();
            for (int i = 0; i < paramsNumber; i++) {
                params.add(scanner.nextDouble());
            }
            Item item = new Item(params, "");
            System.out.println("Answer is: " + kNN(k, item, trainSet));
            System.out.print("\nWould you like to run kNN for input parameters?(yes/no): ");
            input = scanner.next();
        }
    }

    static double distance(List<Double> params1, List<Double> params2){
        double sum = 0;
        for (int i = 0; i < params1.size(); i++) {
            sum += Math.pow((params1.get(i)-params2.get(i)),2);
        }
        return Math.sqrt(sum);
    }


}