package org.example.lab01.service.impl;

import org.example.lab01.model.Location;
import org.example.lab01.model.exceptions.LocationNotFoundExc;
import org.example.lab01.repository.LocationRepo;
import org.example.lab01.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepo locationRepo;

    public LocationServiceImpl(LocationRepo locationRepo) {
        this.locationRepo = locationRepo;
    }

    @Override
    public List<Location> listAll() {
        return this.locationRepo.findAll();
    }

    @Override
    public Location findById(Long id) {
        return this.locationRepo.findById(id).orElseThrow(LocationNotFoundExc::new);
    }

    @Override
    public void deleteById(Long id) {
        this.locationRepo.deleteById(id);

    }

    @Override
    public Location create(String name, String continent) {
       Location c= new Location(name,continent);
       return this.locationRepo.save(c);
    }

    @Override
    public Location update(Long id, String name, String continent) {
        Location c = this.locationRepo.findById(id).orElseThrow(LocationNotFoundExc::new);
        c.setName(name);
        c.setContinent(continent);
        return this.locationRepo.save(c);
    }
}
