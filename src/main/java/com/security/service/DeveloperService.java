package com.security.service;

import com.security.entity.Developer;
import com.security.repository.DeveloperRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class DeveloperService {
    private final DeveloperRepo developerRepo;

    /**
     * CREATE operation for Developer
     * @param developer
     */
    public boolean saveDeveloper(Developer developer) {
        try {
            developerRepo.save(developer);
            log.info("Developer is successfully saved into DB: {}", developer);
            return true;
        } catch (Exception e) {
            log.error("Exception occurred while saving developer to DB: {},{}", e.getMessage(), e);
        }
        return false;
    }

    /**
     * UPDATE operation for Developer
     * @param developer
     */
    public boolean updateDeveloper(Developer developer) {
        try {
            Optional<Developer> developerFromDB = developerRepo.findById(developer.getId());
            if(developerFromDB.isPresent()) {
                developerFromDB.get().setCompanyName(developer.getCompanyName());
                developerFromDB.get().setName(developer.getName());
                developerFromDB.get().setExperience(developer.getExperience());
                developerFromDB.get().setPrimaryLanguage(developer.getPrimaryLanguage());
                developerRepo.save(developerFromDB.get());
                log.info("Developer with ID: {} is successfully updated", developer.getId());
                return true;
            } else {
                log.error("No Developer exists with the passed ID: {}", developer.getId());
            }
        } catch (Exception e) {
            log.error("Exception occurred while updating developer: {},{}", e.getMessage(), e);
        }
        return false;
    }

    /**
     * READ operation for Developer
     * @return
     */
    public List<Developer> getAllDevelopers(Integer pageNumber, Integer pageSize) {
        try {
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
            Page<Developer> developersPage = developerRepo.findAll(pageable);
            List<Developer> developers = developersPage.getContent();
            log.info("Developers List: {}", developers.toString());
            return developers;
        } catch (Exception e) {
            log.error("Exception occurred while fetching all developers: {},{}", e.getMessage(), e);
        }
        return null;
    }

    /**
     * DELETE operation for Developer
     * @param id
     * @return
     */
    public boolean deleteDeveloperById(Long id) {
        try {
            developerRepo.deleteById(id);
            log.info("Developer with ID: {} is successfully updated", id);
            return true;
        } catch (Exception e) {
            log.error("Exception occurred while deleting developer: {},{}", e.getMessage(), e);
        }
        return false;
    }
}
