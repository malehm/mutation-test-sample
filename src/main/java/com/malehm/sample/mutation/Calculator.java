package com.malehm.sample.mutation;

final class Calculator {

  private Calculator() {}

  static int add(final int a, final int b) {
    return a + b;
  }

  static int substract(final int a, final int b) {
    return a - b;
  }

  static int multiply(final int a, final int b) {
    return a * b;
  }

  static double divide(final int a, final int b) {
    if (b == 0) {
      throw new RuntimeException("division by zero");
    }
    return a / b;
  }

}
