package de.rpr.junit.theories;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.ParameterSignature;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RandomIntegerSupplierTest {

  RandomIntegerSupplier supplier;
  @Mock
  RandomInteger mockAnnotation;
  @Mock
  ParameterSignature paramSignature;

  @Before
  public void setup() {
    supplier = new RandomIntegerSupplier();
  }

  @Test
  public void missingAnnotationThrowsException() {
    assertThatExceptionOfType(IllegalStateException.class).isThrownBy(() -> supplier.getStream(paramSignature));
  }

  @Test
  public void supplierReturnsValuesBelowUpperBound() {
    when(paramSignature.getAnnotation(any())).thenReturn(mockAnnotation);
    when(mockAnnotation.upperBound()).thenReturn(1);
    when(mockAnnotation.numValues()).thenReturn(1);
    List<Integer> result = supplier.getStream(paramSignature).collect(Collectors.toList());
    assertThat(result.get(0)).isLessThanOrEqualTo(1);
  }

  @Test
  public void supplierReturnExpectedNumberOfValues() {
    when(paramSignature.getAnnotation(any())).thenReturn(mockAnnotation);
    when(mockAnnotation.upperBound()).thenReturn(1);
    when(mockAnnotation.numValues()).thenReturn(2);
    List<Integer> result = supplier.getStream(paramSignature).collect(Collectors.toList());
    assertThat(result).hasSize(2);

  }
}