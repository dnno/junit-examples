package de.rpr.junit.theories;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.ParameterSignature;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LinearIntegerSupplierTest {

  LinearIntegerSupplier supplier;
  @Mock
  ParameterSignature paramSignature;
  @Mock
  IntegerInRange mockAnnotation;

  @Before
  public void setup() {
    supplier = new LinearIntegerSupplier();
  }

  @Test
  public void missingAnnotationThrowsException() {
    assertThatExceptionOfType(IllegalStateException.class).isThrownBy(() -> supplier.getStream(paramSignature));
  }

  @Test
  @SuppressWarnings("unchecked")
  public void supplierUsesCorrectAnnotation() {
    when(paramSignature.getAnnotation(any())).thenReturn(mockAnnotation);
    supplier.getStream(paramSignature);
    ArgumentCaptor<Class> captor = ArgumentCaptor.forClass(Class.class);
    verify(paramSignature).getAnnotation(captor.capture());
    assertThat(captor.getValue()).isEqualTo(IntegerInRange.class);
  }

  @Test
  public void streamHasExpectedNumberOfElements() {
    when(paramSignature.getAnnotation(any())).thenReturn(mockAnnotation);
    when(mockAnnotation.from()).thenReturn(0);
    when(mockAnnotation.to()).thenReturn(2);
    List<Integer> result = supplier.getStream(paramSignature).collect(Collectors.toList());
    assertThat(result).hasSize(3);
  }

  @Test
  public void streamBeginsWithLowerEnd() {
    when(paramSignature.getAnnotation(any())).thenReturn(mockAnnotation);
    when(mockAnnotation.from()).thenReturn(0);
    when(mockAnnotation.to()).thenReturn(2);
    List<Integer> result = supplier.getStream(paramSignature).collect(Collectors.toList());
    assertThat(result.get(0)).isEqualTo(0);
  }

  @Test
  public void streamEndsWithExclusiveUpperEnd() {
    when(paramSignature.getAnnotation(any())).thenReturn(mockAnnotation);
    when(mockAnnotation.from()).thenReturn(0);
    when(mockAnnotation.to()).thenReturn(2);
    List<Integer> result = supplier.getStream(paramSignature).collect(Collectors.toList());
    assertThat(result.get(2)).isEqualTo(2);
  }

  @Test
  public void reversedStreamStartsWithUpperEnd() {
    when(paramSignature.getAnnotation(any())).thenReturn(mockAnnotation);
    when(mockAnnotation.from()).thenReturn(0);
    when(mockAnnotation.to()).thenReturn(2);
    when(mockAnnotation.reverse()).thenReturn(true);
    List<Integer> result = supplier.getStream(paramSignature).collect(Collectors.toList());
    assertThat(result.get(0)).isEqualTo(2);
  }

  @Test
  public void reversedStreamEndsithLowerEnd() {
    when(paramSignature.getAnnotation(any())).thenReturn(mockAnnotation);
    when(mockAnnotation.from()).thenReturn(0);
    when(mockAnnotation.to()).thenReturn(2);
    when(mockAnnotation.reverse()).thenReturn(true);
    List<Integer> result = supplier.getStream(paramSignature).collect(Collectors.toList());
    assertThat(result.get(2)).isEqualTo(0);
  }

}