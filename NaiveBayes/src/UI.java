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
                case 4 -> System.out.println();
                case 5 -> {
                    scanner.close();
                    return;
                }
            }
        }
    }

    private static int readOption(){
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
}
