package com.example.springDemo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CityDto {

    private Long id;

    private String name;

    private String district;

    private String country;
}
