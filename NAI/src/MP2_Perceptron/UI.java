package MP2_Perceptron;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {
    static Scanner scanner = new Scanner(System.in);
    private static final String[] optionsMenu = {
            "\nPlease select option:",
            "Test perceptron",
            "Train perceptron",
            "Provide test vector manually",
            "Change learning ratio",
            "Change target class",
            "Quit"};

    public static void start(Trainer trainer){
        Perceptron p = trainer.createPerceptron();
        trainer.trainPerceptron(p);

        while (true) {
            int option = readOption();
            System.out.println();
            switch (option){
                case 1 -> trainer.testPerceptron(p);
                case 2 -> trainer.trainPerceptron(p);
                case 3 -> checkForInput(trainer, p);
                case 4 -> {
                    changeA(trainer);
                    System.out.println("Trainer learning ratio updated.");
                    System.out.println(trainer);
                }
                case 5 -> {
                    changeTargetClass(trainer);
                    System.out.println("Trainer target class updated.");
                    System.out.println(trainer);
                }
                case 6 -> {
                    scanner.close();
                    return;
                }
            }
        }
    }

    private static void checkForInput(Trainer trainer, Perceptron p){
        while (true) {
            System.out.print("Please enter " + trainer.trainSet.entryParamsNumber + " values: ");
            List<Double> params = new ArrayList<>();
            try {
                for (int i = 0; i < trainer.trainSet.entryParamsNumber; i++) {
                    params.add(Double.parseDouble(scanner.next()));
                }
                System.out.println("y=" + p.compute(params));
                return;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input - please try again");
            }
        }
    }

    private static void changeA(Trainer trainer) {
        System.out.println("Current a value: " + trainer.a);
        System.out.print("Please provide new value between 0 and 1 (exclusive): ");
        while (true) {
            String input = scanner.nextLine();
            try {
                double temp = Double.parseDouble(input);
                if (temp > 0 && temp < 1){
                    trainer.a = temp;
                    return;
                }
            } catch (NumberFormatException ignored) {}
        }
    }

    private static void changeTargetClass(Trainer trainer) {
        System.out.println("Classes present in train set: ");
        int counter = 0;
        for (String label : trainer.trainSet.labels){
            System.out.println("(" + counter + ") " + label);
            counter++;
        }
        System.out.print("Please select a number of desired class: ");
        while (true) {
            try {
                int option = Integer.parseInt(scanner.next());
                if (option >= 0 && option <= trainer.trainSet.labels.size()) {
                    counter = 0;
                    for (String label : trainer.trainSet.labels){
                        if (counter == option) {
                            trainer.targetClassName = label;
                            return;
                        } else counter++;
                    }
                }
            } catch (NumberFormatException ignored) {}
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
