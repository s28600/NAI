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

    ArrayList<double[]> initializeCentroids(){
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
}
