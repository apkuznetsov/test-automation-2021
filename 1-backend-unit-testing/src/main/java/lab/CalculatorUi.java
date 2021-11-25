package lab;

import java.util.Scanner;

import static lab.Calculator.*;
import static lab.CalculatorUis.*;

public class CalculatorUi {

    private final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        CalculatorUi calculator = new CalculatorUi();
        calculator.run();
    }

    private int inputNumber() {
        while (!in.hasNextInt()) {
            in.next();
            System.out.print(INCORRECT_INPUT);
        }

        return in.nextInt();
    }

    private Operation inputOperation() {
        Operation result = Operation.NONE;

        boolean doesEqual = false;
        do {
            String line = in.next();

            for (Operation operation : Operation.values()) {
                if (operation.SYMBOL.equalsIgnoreCase(line)) {
                    doesEqual = true;
                    result = operation;
                    break;
                }
            }

            if (!doesEqual) {
                System.out.print(INCORRECT_CHOICE);
            }
        } while (!doesEqual);

        return result;
    }

    public void run() {
        while (true) {
            System.out.print(INPUT_A);
            int a = inputNumber();
            System.out.println(String.format(TO_BINARY, a, toBinary(a)) + "\n");

            System.out.print(INPUT_B);
            int b = inputNumber();
            System.out.println(String.format(TO_BINARY, b, toBinary(b)) + "\n");

            System.out.print(CHOOSE_OPERATION);
            Operation operation = inputOperation();
            System.out.println(String.format(CHOSEN_OPERATION, operation) + "\n");

            int result = calculate(a, b, operation);
            System.out.println(THERE_IS_BINARY);
            System.out.println(String.format(OPERATION_RESULT, toBinary(a), operation, toBinary(b), toBinary(result)));
            System.out.println(THERE_IS_DECIMAL);
            System.out.println(String.format(OPERATION_RESULT, a, operation, b, result) + "\n");

            System.out.println(REPEAT_OR_EXIT);
            in.nextLine();
            String line = in.nextLine();
            if (!line.equalsIgnoreCase(REPEAT)) {
                in.close();
                break;
            }
            System.out.println();
        }
    }
}