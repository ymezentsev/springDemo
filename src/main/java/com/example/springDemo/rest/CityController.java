package com.example.springDemo.rest;

import com.example.springDemo.dto.CityDto;
import com.example.springDemo.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping("/cities")
    public ResponseEntity<List<CityDto>> findAll() {
        return ResponseEntity.ok(cityService.findAll());
    }

    @GetMapping("/cities/{id}")
    public ResponseEntity<CityDto> findById(@PathVariable Long id){
        return cityService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cities/name/{name}")
    public ResponseEntity<CityDto> findByName(@PathVariable String name){
        return cityService.findByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/cities")
    public ResponseEntity<Void> save(@RequestBody CityDto cityDto){
        cityService.save(cityDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
