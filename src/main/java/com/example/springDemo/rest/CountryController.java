package com.example.springDemo.rest;

import com.example.springDemo.dto.CountryDto;
import com.example.springDemo.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @GetMapping("/countries")
    public ResponseEntity<List<CountryDto>> findAll() {
        return ResponseEntity.ok(countryService.findAll());
    }

    @GetMapping("/countries/{id}")
    public ResponseEntity<CountryDto> findById(@PathVariable Long id){
        return countryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/countries/name/{name}")
    public ResponseEntity<CountryDto> findByName(@PathVariable String name){
        return countryService.findByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/countries")
    public ResponseEntity<Void> save(@RequestBody CountryDto countryDto){
        countryService.save(countryDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
