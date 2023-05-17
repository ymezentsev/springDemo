package com.example.springDemo.service;

import com.example.springDemo.domain.City;
import com.example.springDemo.domain.Country;
import com.example.springDemo.dto.CityDto;
import com.example.springDemo.repository.CityRepository;
import com.example.springDemo.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    public List<CityDto> findAll() {
        return cityRepository.findAll().stream().map(CityService::buildCityDto).collect(Collectors.toList());
    }

    private static CityDto buildCityDto(City city) {
        var countryName = "NO COUNTRY";
        if (city.getCountry() != null) {
            countryName = city.getCountry().getName();
        }

        return CityDto.builder()
                .id(city.getId())
                .name(city.getName())
                .district(city.getDistrict())
                .country(countryName)
                .build();
    }

    public Optional<CityDto> findById(Long id) {
        return cityRepository.findById(id).map(CityService::buildCityDto);
    }

    public Optional<CityDto> findByName(String name) {
        return cityRepository.findCityByName(name).map(CityService::buildCityDto);
    }

    public void save(CityDto cityDto) {
        String countryName = cityDto.getCountry();
        Country country = null;

        if(countryRepository.findCountryByName(countryName).isPresent()){
            country = countryRepository.findCountryByName(countryName).get();
        } else {
            country = Country.builder().name(countryName).build();
            countryRepository.save(country);
        }

        City city = City.builder()
                .name(cityDto.getName())
                .district(cityDto.getDistrict())
                .country(country)
                .build();

        cityRepository.save(city);
    }
}
