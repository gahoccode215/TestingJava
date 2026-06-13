import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DisplayName("Test Math operations in Calculator Test")
public class CalculatorTest {
    Calculator calculator;

    @BeforeEach
    void beforeEachTestMethod() {
         calculator = new Calculator();
    }

    @DisplayName("Division by zero")
    @Test
    void testIntegerDivision_WhenFourIsDividedByTwo_ShouldReturnTwo(){
        // Given
        int dividend = 4;
        int divisor = 2;
        int expectedResult = 2;
        // When
        int actualResult = calculator.divide(4, 2);
        // Then
        String errorMsg = "4/2 did not produce 2";
        assertEquals(expectedResult, actualResult, errorMsg);
    }

    @DisplayName("Division by zero throw Arithmetic Exception")
    @Test
    void testIntegerDivision_WhenDividendIsDividedByZero_ShouldThrowArithmeticException(){
        int dividend = 4;
        int divisor = 0;
        String expectedExceptionMessage = "/ by zero";

        ArithmeticException actualException = assertThrows(ArithmeticException.class, () -> {
            calculator.divide(dividend, divisor);
        }, "Division by zero should have thrown an Arithmetic exception.");

        assertEquals(expectedExceptionMessage, actualException.getMessage(), "Unexpected exception message");
    }

    @DisplayName("Subtract two integer")
    @ParameterizedTest
    @MethodSource()
    void testIntegerSubtraction_WhenSubtractingTwoIntegers_ShouldReturnTheDifference(int minuend, int subtrahend, int expectedResult){
        int actualResult = calculator.subtract(minuend, subtrahend);
        assertEquals(expectedResult, actualResult, "Subtraction did not produce the expected result");
    }

    private static Stream<Arguments> testIntegerSubtraction_WhenSubtractingTwoIntegers_ShouldReturnTheDifference(){
        return Stream.of(
            Arguments.of(1, 2, -1),
            Arguments.of(2, 1, 1),
            Arguments.of(0, 0, 0)
        );
    }
}
