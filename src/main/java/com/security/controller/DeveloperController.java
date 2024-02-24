package com.security.controller;

import com.security.constants.SecurityConstants;
import com.security.entity.Developer;
import com.security.service.DeveloperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/developer")
public class DeveloperController {
    private final DeveloperService developerService;

    @PostMapping("/create")
    public ResponseEntity<String> createNewDeveloper(@RequestBody Developer developer) {
        boolean status = developerService.saveDeveloper(developer);
        if(status)
            return new ResponseEntity<>(SecurityConstants.SUCCESS, HttpStatus.OK);
        else
            return new ResponseEntity<>(SecurityConstants.FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateExistingDeveloper(@RequestBody Developer developer) {
        boolean status = developerService.updateDeveloper(developer);
        if(status)
            return new ResponseEntity<>(SecurityConstants.SUCCESS, HttpStatus.OK);
        else
            return new ResponseEntity<>(SecurityConstants.FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/getAllDeveloper")
    public ResponseEntity<List<Developer>> getAllDevelopers() {
        List<Developer> developers = developerService.getAllDevelopers();
        if(developers != null)
            return new ResponseEntity<>(developers, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteDeveloperById(@PathVariable Long id) {
        boolean status = developerService.deleteDeveloperById(id);
        if(status)
            return new ResponseEntity<>(SecurityConstants.SUCCESS, HttpStatus.OK);
        else
            return new ResponseEntity<>(SecurityConstants.FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
