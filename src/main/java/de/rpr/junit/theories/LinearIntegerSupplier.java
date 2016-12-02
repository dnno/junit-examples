package de.rpr.junit.theories;

import org.junit.experimental.theories.ParameterSignature;

import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LinearIntegerSupplier extends AbstractIntegerSupplier {

  @Override
  Stream<Integer> getStream(final ParameterSignature signature) {
    IntegerInRange annotation = signature.getAnnotation(IntegerInRange.class);
    if (annotation == null) {
      throw new IllegalStateException("Missing annotation: @IntegerInRange");
    }
    Stream<Integer> intStream = IntStream.range(annotation.from(), annotation.to() + 1).boxed();

    if (annotation.reverse()) {
      return intStream.sorted(Comparator.reverseOrder());
    } else {
      return intStream;
    }
  }
}
