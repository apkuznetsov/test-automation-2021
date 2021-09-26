package lab;

public class Calculator {
    public static int add(int a, int b) {
        return a + b;
    }

    public static int subtract(int a, int b) {
        return a - b;
    }

    public static int multiply(int a, int b) {
        return a * b;
    }

    public static int divide(int a, int b) {
        return (int) Math.round((double) a / (double) b);
    }

    public static int xor(int a, int b) {
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
