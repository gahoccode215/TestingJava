import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


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

}
