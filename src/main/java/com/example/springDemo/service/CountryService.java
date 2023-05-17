package com.example.springDemo.service;

import com.example.springDemo.domain.City;
import com.example.springDemo.domain.Country;
import com.example.springDemo.dto.CountryDto;
import com.example.springDemo.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    public List<CountryDto> findAll() {
        return countryRepository.findAll().stream().map(CountryService::buildCountryDto).collect(Collectors.toList());
    }

    private static CountryDto buildCountryDto(Country country) {
        return CountryDto.builder()
                .id(country.getId())
                .name(country.getName())
                .region(country.getRegion())
                .cities(country.getCities().stream()
                        .map(City::getName)
                        .collect(Collectors.toList()))
                .build();
    }

    public Optional<CountryDto> findById(Long id) {
        return countryRepository.findById(id).map(CountryService::buildCountryDto);
    }

    public Optional<CountryDto> findByName(String name) {
        return countryRepository.findCountryByName(name).map(CountryService::buildCountryDto);
    }

    public void save(CountryDto countryDto) {
        Country country = Country.builder()
                .name(countryDto.getName())
                .region(countryDto.getRegion())
                .build();

        countryRepository.save(country);
    }
}
