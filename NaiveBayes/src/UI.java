import java.util.Scanner;

public class UI {
    static Scanner scanner = new Scanner(System.in);
    private static final String[] optionsMenu = {
            "\nPlease select option:",
            "Check for train set",
            "Check for test set",
            "Toggle debug mode",
            "Enter manually",
            "Quit"};
    Bayes bayes;

    public UI(Bayes bayes) {
        this.bayes = bayes;
    }

    public void start(){
        while (true) {
            int option = readOption();
            System.out.println();
            switch (option){
                case 1 -> bayes.checkTrainSet();
                case 2 -> bayes.checkTestSet(Util.readFile("data/testset.csv"));
                case 3 -> Bayes.DEBUG=!Bayes.DEBUG;
                case 4 -> checkForInput();
                case 5 -> {
                    scanner.close();
                    return;
                }
            }
        }
    }

    private int readOption(){
        System.out.println(optionsMenu[0]);
        for (int i = 1; i < optionsMenu.length; i++) {
            System.out.println("(" + i + ") " + optionsMenu[i]);
        }
        while (true) {
            try {
                System.out.print("-> ");
                int option = Integer.parseInt(scanner.next());
                if (option >= 1 && option <= optionsMenu.length-1) {
                    return option;
                }
            } catch (NumberFormatException ignored) {}
        }
    }

    private void checkForInput(){
        while (true) {
            System.out.println("Available attributes by index:");
            for (Integer key : bayes.availableAttributes.keySet()){
                System.out.println("\t" + key + " = " + bayes.availableAttributes.get(key));
            }
            System.out.print("Please enter " + bayes.availableAttributes.size() + " attributes: ");
            String[] line = new String[bayes.availableAttributes.size()];
            try {
                for (int i = 0; i < bayes.availableAttributes.size(); i++) {
                    line[i] = scanner.next();
                }
                System.out.println("Verdict: " + bayes.getVerdict(line));
                return;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input - please try again");
            }
        }
    }
}
