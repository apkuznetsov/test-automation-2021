package lab;

public class Calculator {
    private static int add(int a, int b) {
        return a + b;
    }

    private static int subtract(int a, int b) {
        return a - b;
    }

    private static int multiply(int a, int b) {
        return a * b;
    }

    private static int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException();
        }

        double res = (double) a / (double) b;
        return res > 0 ? (int) res : (int) (Math.ceil(-res)) * -1;
    }

    private static int xor(int a, int b) {
        return a ^ b;
    }

    public static int calculate(int a, int b, Operation operation) {
        int result = 0;

        switch (operation) {
            case ADD:
                result = Calculator.add(a, b);
                break;
            case SUBTRACT:
                result = Calculator.subtract(a, b);
                break;
            case MULTIPLY:
                result = Calculator.multiply(a, b);
                break;
            case DIVIDE:
                result = Calculator.divide(a, b);
                break;
            case XOR:
                result = Calculator.xor(a, b);
                break;
        }

        return result;
    }

    public static String toBinary(int number) {
        return Integer.toBinaryString(number);
    }

    public enum Operation {

        ADD("+"),
        SUBTRACT("-"),
        MULTIPLY("*"),
        DIVIDE("/"),
        XOR("^"),
        NONE("0");

        public final String SYMBOL;

        Operation(String symbol) {
            this.SYMBOL = symbol;
        }

        @Override
        public String toString() {
            return SYMBOL;
        }
    }
}
