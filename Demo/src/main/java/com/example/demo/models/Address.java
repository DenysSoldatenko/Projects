package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents an address with country, city, and postal code information.
 */
@Data
@AllArgsConstructor
public class Address {
  private String country;
  private String city;
  private String postCode;
}