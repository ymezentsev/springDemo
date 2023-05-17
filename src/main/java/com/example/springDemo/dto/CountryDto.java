package com.example.springDemo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CountryDto {

    private Long id;

    private String name;

    private String region;

    private List<String> cities;
}
