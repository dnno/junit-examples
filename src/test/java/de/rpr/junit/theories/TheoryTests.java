package de.rpr.junit.theories;

import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(Theories.class)
public class TheoryTests {

  @Theory
  public void linearTest(@IntegerInRange(to = 10) int number) {
    assertTrue(new Validator().validate(number));
  }

  @Theory
  public void randomTest(@RandomInteger(numValues = 10, upperBound = 10) int number) {
    assertTrue(new Validator().validate(number));
  }

  // Theories runner will invoke test with all combinations of the two parameters.
  @Theory
  public void linearTestWithMultipleParameters(
      @IntegerInRange(to = 5) int left,
      @IntegerInRange(to = 2) int right) {
    assertTrue(new Validator().validate(left));
    assertTrue(new Validator().validate(right));
  }

  // Theories runner will invoke test with all combinations of the two parameters.
  @Theory
  public void randomTestWithMultipleParameters(
      @RandomInteger(numValues = 10, upperBound = 4) int left,
      @RandomInteger(numValues = 10, upperBound = 8) int right) {
    assertTrue(new Validator().validate(left));
    assertTrue(new Validator().validate(right));
  }

  class Validator {

    boolean validate(int value) {
      return value <= 10;
    }
  }
}
