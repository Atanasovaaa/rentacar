package com.uni_projects.rentacar.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CityNameType {
    PLOVDIV("Plovdiv"),
    SOFIA("Sofia"),
    VARNA("Varna"),
    BURGAS("Burgas");

    private final String cityNameType;

    CityNameType(String cityNameType) {
        this.cityNameType = cityNameType;
    }

    @JsonValue
    public String getCityNameType() {
        return cityNameType;
    }

    @JsonCreator
    public static CityNameType fromValue(String value) {
        for (CityNameType cityName : values()) {
            String currentCityName = cityName.getCityNameType();
            if (currentCityName.equals(value)) {
                return cityName;
            }
        }

        throw new IllegalArgumentException("Invalid value for city name: " + value);
    }
}
