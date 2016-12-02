package de.rpr.junit.theories;

import org.junit.experimental.theories.ParameterSignature;

import java.security.SecureRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RandomIntegerSupplier extends AbstractIntegerSupplier {

  @Override
  Stream<Integer> getStream(final ParameterSignature signature) {
    RandomInteger annotation = signature.getAnnotation(RandomInteger.class);
    if (annotation == null) {
      throw new IllegalStateException("Missing annotation: @RandomInteger");
    }
    SecureRandom random = new SecureRandom();
    return IntStream
        .range(0, annotation.numValues())
        .map(value -> random.nextInt(annotation.upperBound() + 1))
        .boxed();
  }
}
