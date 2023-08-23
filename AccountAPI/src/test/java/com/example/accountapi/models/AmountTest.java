package com.example.accountapi.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Amount class.
 */
public class AmountTest {

  @Test
  public void testAmountConstructorAndGetters() {
    Amount amount = new Amount(100.0f);

    assertEquals(100.0f, amount.total());
  }

  @Test
  public void testAmountEquality() {
    Amount amount1 = new Amount(100.0f);
    Amount amount2 = new Amount(100.0f);

    assertEquals(amount1.total(), amount2.total());
  }

  @Test
  public void testAmountInequality() {
    Amount amount1 = new Amount(100.0f);
    Amount amount2 = new Amount(200.0f);

    assertNotEquals(amount1.total(), amount2.total());
  }
}
