import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        double a;
        DataSet trainSet;
        DataSet testSet;

        try {
            a = Double.parseDouble(args[0]);
            if (a <= 0 || a >= 1){
                System.out.println("Invalid learning rate - please provide value between 0 and 1 (exclusive)");
                return;
            }
            trainSet = new DataSet(args[1]);
            testSet = new DataSet(args[2]);
        } catch (NumberFormatException e){
            System.out.println("Argument not convertable to double - please provide correct argument");
            return;
        } catch (FileNotFoundException e) {
            System.out.println("File not found - invalid pathname of at least one file");
            return;
        } catch (Exception e) {
            System.out.printf(e.getMessage());
            return;
        }

        Trainer trainer = new Trainer(a, trainSet, testSet);
        UI.start(trainer);
    }
}
