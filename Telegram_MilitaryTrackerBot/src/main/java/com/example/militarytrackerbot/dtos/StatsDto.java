package com.example.militarytrackerbot.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * DTO representing the 'stats' or 'increase' object, which contains various military statistics.
 */
@Data
public class StatsDto {

  @JsonProperty("personnel_units")
  private int personnelUnits;

  @JsonProperty("tanks")
  private int tanks;

  @JsonProperty("armoured_fighting_vehicles")
  private int armouredFightingVehicles;

  @JsonProperty("artillery_systems")
  private int artillerySystems;

  @JsonProperty("aa_warfare_systems")
  private int aaWarfareSystems;

  @JsonProperty("planes")
  private int planes;

  @JsonProperty("helicopters")
  private int helicopters;

  @JsonProperty("vehicles_fuel_tanks")
  private int vehiclesFuelTanks;

  @JsonProperty("warships_cutters")
  private int warshipsCutters;

  @JsonProperty("cruise_missiles")
  private int cruiseMissiles;

  @JsonProperty("uav_systems")
  private int uavSystems;

  @JsonProperty("special_military_equip")
  private int specialMilitaryEquip;

  @JsonProperty("submarines")
  private int submarines;
}