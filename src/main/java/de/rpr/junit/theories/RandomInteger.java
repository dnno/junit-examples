package de.rpr.junit.theories;

import org.junit.experimental.theories.ParametersSuppliedBy;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Apply a {@link RandomIntegerSupplier} to an Integer parameter of a test.
 */
@Retention(RetentionPolicy.RUNTIME)
@ParametersSuppliedBy(RandomIntegerSupplier.class)
public @interface RandomInteger {

  /**
   * Number of values to create.
   * 
   * @return
   */
  int numValues();

  /**
   * The maximum value (inklusive) for each of the generated numbers.
   * 
   * @return
   */
  int upperBound() default Integer.MAX_VALUE;

}
