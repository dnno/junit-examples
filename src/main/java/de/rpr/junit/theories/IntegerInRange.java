package de.rpr.junit.theories;

import org.junit.experimental.theories.ParametersSuppliedBy;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Apply a {@link LinearIntegerSupplier} to an Integer parameter of a test.
 */
@Retention(RetentionPolicy.RUNTIME)
@ParametersSuppliedBy(LinearIntegerSupplier.class)
public @interface IntegerInRange {

  /**
   * Inclusive lower end.
   * 
   * @return
   */
  int from() default 0;

  /**
   * Inclusive upper end.
   * 
   * @return
   */
  int to() default 0;

  /**
   * Reverse the resulting numbers.
   * 
   * @return
   */
  boolean reverse() default false;
}
