package lab;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class CalculatorTest {

    @DisplayName("add возвращает правильную сумму")
    @ParameterizedTest
    @CsvFileSource(resources = "CalculatorTestDataset1.csv", numLinesToSkip = 1, delimiter = ';')
    void add_ShouldReturnCorrectSum(int a, int b, int expectedSum, int expectedDifference, int expectedProduct, String expectedQuotient, int expectedXor) {
        // act
        int res = Calculator.calculate(a, b, Calculator.Operation.ADD);

        // assert
        assertEquals(expectedSum, res);
    }

    @DisplayName("subtract возвращает правильную разность")
    @ParameterizedTest
    @CsvFileSource(resources = "CalculatorTestDataset1.csv", numLinesToSkip = 1, delimiter = ';')
    void subtract_ShouldReturnCorrectDifference(int a, int b, int expectedSum, int expectedDifference, int expectedProduct, String expectedQuotient, int expectedXor) {
        // act
        int res = Calculator.calculate(a, b, Calculator.Operation.SUBTRACT);

        // assert
        assertEquals(expectedDifference, res);
    }

    @DisplayName("multiply возвращает правильное произведение")
    @ParameterizedTest
    @CsvFileSource(resources = "CalculatorTestDataset1.csv", numLinesToSkip = 1, delimiter = ';')
    void multiply_ShouldReturnCorrectProduct(int a, int b, int expectedSum, int expectedDifference, int expectedProduct, String expectedQuotient, int expectedXor) {
        // act
        int res = Calculator.calculate(a, b, Calculator.Operation.MULTIPLY);

        // assert
        assertEquals(expectedProduct, res);
    }

    @DisplayName("divide выбрасывает исключение при делении на ноль")
    @ParameterizedTest
    @CsvFileSource(resources = "CalculatorTestDataset1.csv", numLinesToSkip = 1, delimiter = ';')
    void divide_ShouldThrowExceptionIfDivisorIsZero(int a, int b, int expectedSum, int expectedDifference, int expectedProduct, String expectedQuotient, int expectedXor) {
        // arrange
        if (b != 0) {
            return;
        }

        // act then assert
        assertThrows(ArithmeticException.class, () ->
                Calculator.calculate(a, b, Calculator.Operation.DIVIDE));
    }

    @DisplayName("divide возвращает правильное частное")
    @ParameterizedTest
    @CsvFileSource(resources = "CalculatorTestDataset1.csv", numLinesToSkip = 1, delimiter = ';')
    void divide_ShouldReturnCorrectQuotient(int a, int b, int expectedSum, int expectedDifference, int expectedProduct, String expectedQuotient, int expectedXor) {
        // arrange
        boolean shouldSkipThisParams = expectedQuotient == null
                || expectedQuotient.equalsIgnoreCase("")
                || expectedQuotient.equalsIgnoreCase("inf")
                || expectedQuotient.equalsIgnoreCase("-inf");
        if (shouldSkipThisParams)
            return;
        int expectedValue = (int) Double.parseDouble(expectedQuotient);

        // act
        int res = Calculator.calculate(a, b, Calculator.Operation.DIVIDE);

        // assert
        assertEquals(expectedValue, res);
    }

    @DisplayName("xor возвращает правильное число")
    @ParameterizedTest
    @CsvFileSource(resources = "CalculatorTestDataset1.csv", numLinesToSkip = 1, delimiter = ';')
    void xor_ShouldReturnCorrectXor(int a, int b, int expectedSum, int expectedDifference, int expectedProduct, String expectedQuotient, int expectedXor) {
        // act
        int res = Calculator.calculate(a, b, Calculator.Operation.XOR);

        // assert
        assertEquals(expectedXor, res);
    }

    private DynamicTest buildTest(final int a, final int b, final Calculator.Operation operator, final int expected) {
        String displayName = String.format("%s %s %s = %s", a, operator.SYMBOL, b, expected);

        return dynamicTest(displayName, () -> {
            int res = Calculator.calculate(a, b, operator);
            assertEquals(expected, res);
        });
    }

    private void buildTestsFromFile(Collection<DynamicTest> tests, String fileName) {
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            String line;
            in.readLine(); // пропуск заголовков столбцов

            String[] cells;
            int a;
            int b;
            int expectedSum;
            int expectedDifference;
            int expectedProduct;
            int expectedXor;

            int expectedValue;
            String expectedQuotient;

            while ((line = in.readLine()) != null) {
                cells = line.split(";");
                a = Integer.parseInt(cells[0]);
                b = Integer.parseInt(cells[1]);

                expectedSum = Integer.parseInt(cells[2]);
                expectedDifference = Integer.parseInt(cells[3]);
                expectedProduct = Integer.parseInt(cells[4]);
                expectedXor = Integer.parseInt(cells[6]);

                tests.add(buildTest(a, b, Calculator.Operation.ADD, expectedSum));
                tests.add(buildTest(a, b, Calculator.Operation.SUBTRACT, expectedDifference));
                tests.add(buildTest(a, b, Calculator.Operation.MULTIPLY, expectedProduct));
                tests.add(buildTest(a, b, Calculator.Operation.XOR, expectedXor));

                expectedQuotient = cells[5];
                boolean shouldSkipThisParams = expectedQuotient == null
                        || expectedQuotient.equalsIgnoreCase("")
                        || expectedQuotient.equalsIgnoreCase("inf")
                        || expectedQuotient.equalsIgnoreCase("-inf");

                if (!shouldSkipThisParams) {
                    expectedValue = (int) Double.parseDouble(expectedQuotient);
                    tests.add(buildTest(a, b, Calculator.Operation.DIVIDE, expectedValue));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DisplayName("динамический тест из всех *.csv в текущей папке")
    @TestFactory
    Collection<DynamicTest> runDynamicTest() {
        Collection<DynamicTest> tests = new ArrayList<>();

        Path currRelativePath = Paths.get("");
        String currFolder = currRelativePath.toAbsolutePath().toString();
        File folder = new File(currFolder);
        String[] folderContent = folder.list();

        for (String file : folderContent) {
            if (file.endsWith(".csv")) {
                buildTestsFromFile(tests, file);
            }
        }

        return tests;
    }
}
