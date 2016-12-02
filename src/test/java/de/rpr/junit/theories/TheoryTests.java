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

  class Validator {

    boolean validate(int value) {
      return value <= 10;
    }
  }
}
