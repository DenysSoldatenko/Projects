package com.example.demo.models;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Address class.
 */
class AddressTest {

  @Test
  void shouldCreateAddress() {
    Address address = new Address();
    address.setCountry("USA");
    address.setCity("New York");
    address.setPostCode("10001");

    assertThat(address.getCountry()).isEqualTo("USA");
    assertThat(address.getCity()).isEqualTo("New York");
    assertThat(address.getPostCode()).isEqualTo("10001");
  }

  @Test
  void shouldCompareAddressesForEquality() {
    Address address1 = new Address();
    address1.setCountry("USA");
    address1.setCity("New York");
    address1.setPostCode("10001");

    Address address2 = new Address();
    address2.setCountry("USA");
    address2.setCity("New York");
    address2.setPostCode("10001");

    Address address3 = new Address();
    address3.setCountry("Canada");
    address3.setCity("Toronto");
    address3.setPostCode("M5V 1J9");

    assertThat(address1)
        .isEqualTo(address2)
        .isNotEqualTo(address3);
  }
}
