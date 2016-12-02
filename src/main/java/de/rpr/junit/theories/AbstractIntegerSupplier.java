package de.rpr.junit.theories;

import org.junit.experimental.theories.ParameterSignature;
import org.junit.experimental.theories.ParameterSupplier;
import org.junit.experimental.theories.PotentialAssignment;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public abstract class AbstractIntegerSupplier extends ParameterSupplier {

  @Override
  public List<PotentialAssignment> getValueSources(final ParameterSignature signature) throws Throwable {
    return getStream(signature)
        .map(AbstractIntegerSupplier::mapValue)
        .collect(toList());
  }

  abstract Stream<Integer> getStream(ParameterSignature signature);

  private static PotentialAssignment mapValue(final Integer number) {
    return PotentialAssignment.forValue(number.toString(), number);
  }
}
