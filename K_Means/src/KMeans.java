import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class KMeans {
    ArrayList<double[]> data;
    int k;

    public KMeans(ArrayList<double[]> data, int k) {
        this.data = data;
        this.k = k;
        System.out.println("\nK-Means initialized:" +
                "\n\t Groups count = " + k);
    }

    public void calculate(){
        ArrayList<double[]> centroids = initializeCentroids();
        HashMap<Integer, ArrayList<double[]>> groups = new HashMap<>();
        double E = 0;

        while (true) {
            groups = getGroups(centroids);
            double currE = calculateE(groups, centroids);

            if (currE == E) {
                break;
            }

            E = currE;
            centroids = calculateCentroids(groups);
        }

        System.out.println("\nGrouping finished:");
        for (var entry : groups.entrySet()){
            System.out.println("Group " + entry.getKey() + ":");
            for (var vector : entry.getValue()){
                System.out.println("\t" + Arrays.toString(vector));
            }
        }
        System.out.println("\nFinal centroids:");
        for (var centroid : centroids){
            System.out.println("\t" + Arrays.toString(centroid));
        }
        System.out.println("\nFinal E = " + E);
    }

    private double calculateE(HashMap<Integer, ArrayList<double[]>> groups, ArrayList<double[]> centroids) {
        double sum = 0;
        for (int i = 0; i < groups.size(); i++) {
            for (int j = 0; j < groups.get(i).size(); j++) {
                sum += Math.pow(calculateCentroidDistance(groups.get(i).get(j), centroids.get(i)), 2);
            }
        }
        return sum;
    }

    private HashMap<Integer, ArrayList<double[]>> getGroups(ArrayList<double[]> centroids) {
        HashMap<Integer, ArrayList<double[]>> groups = new HashMap<>();

        for (double[] row : data) {
            int centroidIndex = 0;
            double distance = calculateCentroidDistance(row, centroids.get(0));
            for (int i = 1; i < k; i++) {
                double currDistance = calculateCentroidDistance(row, centroids.get(i));
                if (currDistance < distance) {
                    distance = currDistance;
                    centroidIndex = i;
                }
            }

            groups.putIfAbsent(centroidIndex, new ArrayList<>());
            groups.get(centroidIndex).add(row);
        }

        return groups;
    }

    private double calculateCentroidDistance(double[] vector, double[] centroid) {
        double sum = 0;
        for (int i = 0; i < vector.length; i++) {
            sum += Math.pow(vector[i] - centroid[i], 2);
        }
        return Math.sqrt(sum);
    }

    private ArrayList<double[]> initializeCentroids(){
        ArrayList<double[]> centroids = new ArrayList<>();
        ArrayList<Integer> usedVectors = new ArrayList<>();
        int i;
        while (centroids.size() < k) {
            i = (int) (Math.random() * data.size());
            if (usedVectors.contains(i)) continue;
            centroids.add(data.get(i));
            usedVectors.add(i);
        }
        System.out.println("\nCentroids initialized:");
        for (var centroid : centroids){
            System.out.println("\t" + Arrays.toString(centroid));
        }
        return centroids;
    }

    private ArrayList<double[]> calculateCentroids(HashMap<Integer, ArrayList<double[]>> groups) {
        ArrayList<double[]> centroids = new ArrayList<>();
        for (int i = 0; i < groups.size(); i++) {
            ArrayList<double[]> group = groups.get(i);
            double[] centroid = new double[group.getFirst().length];
            for (int j = 0; j < centroid.length; j++) {
                int finalJ = j;
                centroid[j] = group.stream().map(x -> x[finalJ]).reduce(0., Double::sum) / group.size();
            }
            centroids.add(centroid);
        }
        return centroids;
    }
}
