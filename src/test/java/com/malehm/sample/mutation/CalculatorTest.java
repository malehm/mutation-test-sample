package com.malehm.sample.mutation;

import java.util.function.BiFunction;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

class CalculatorTest {

  private static final Integer INPUT = 5;

  static class CalculatorArgumentProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(final ExtensionContext context)
        throws Exception {
      return Stream.of(
          Arguments.of((BiFunction<Integer, Integer, Integer>) Calculator::add,
              CalculatorTest.INPUT, 0, CalculatorTest.INPUT),
          Arguments.of((BiFunction<Integer, Integer, Integer>) Calculator::substract,
              CalculatorTest.INPUT, 0, CalculatorTest.INPUT),
          Arguments.of((BiFunction<Integer, Integer, Integer>) Calculator::multiply,
              CalculatorTest.INPUT, 1, CalculatorTest.INPUT),
          Arguments.of((BiFunction<Integer, Integer, Double>) Calculator::divide,
              CalculatorTest.INPUT, 1, CalculatorTest.INPUT.doubleValue()));
    }
  }

  @ParameterizedTest
  @ArgumentsSource(CalculatorArgumentProvider.class)
  void testCalculatorExpectedResultEqualsInput(final BiFunction<Integer, Integer, Number> function,
      final Integer a, final Integer b, final Number expected) {
    Assertions.assertEquals(expected, function.apply(a, b));
  }

  @Test
  void divideByZeroExpectException() {
    final Executable shouldThrowException = () -> Calculator.divide(CalculatorTest.INPUT, 0);
    final Exception exception =
        Assertions.assertThrows(RuntimeException.class, shouldThrowException);
    Assertions.assertEquals("division by zero", exception.getMessage());
  }

}
