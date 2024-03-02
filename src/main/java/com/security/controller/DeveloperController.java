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
    public ResponseEntity<Object> createNewDeveloper(@RequestBody Developer developer) {
        boolean status = developerService.saveDeveloper(developer);
        if(status)
            return new ResponseEntity<>(SecurityConstants.SUCCESS, HttpStatus.OK);
        else
            throw new RuntimeException(SecurityConstants.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateExistingDeveloper(@RequestBody Developer developer) {
        boolean status = developerService.updateDeveloper(developer);
        if(status)
            return new ResponseEntity<>(SecurityConstants.SUCCESS, HttpStatus.OK);
        else
            throw new RuntimeException(SecurityConstants.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/getAllDeveloper")
    public ResponseEntity<Object> getAllDevelopers(
            @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "2", required = false) Integer pageSize
    ) {
        List<Developer> developers = developerService.getAllDevelopers(pageNumber, pageSize);
        if(developers != null)
            return new ResponseEntity<>(developers, HttpStatus.OK);
        else
            throw new RuntimeException(SecurityConstants.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Object> deleteDeveloperById(@PathVariable Long id) {
        boolean status = developerService.deleteDeveloperById(id);
        if(status)
            return new ResponseEntity<>(SecurityConstants.SUCCESS, HttpStatus.OK);
        else
            throw new RuntimeException(SecurityConstants.INTERNAL_SERVER_ERROR);
    }
}
