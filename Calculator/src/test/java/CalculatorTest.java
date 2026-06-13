import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DisplayName("Test Math operations in Calculator Test")
public class CalculatorTest {

    @DisplayName("Division by zero")
    @Test
    void testIntegerDivision_WhenFourIsDividedByTwo_ShouldReturnTwo(){
        // Given
        Calculator calculator = new Calculator();
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
            new Calculator().divide(dividend, divisor);
        }, "Division by zero should have thrown an Arithmetic exception.");

        assertEquals(expectedExceptionMessage, actualException.getMessage(), "Unexpected exception message");
    }

}
