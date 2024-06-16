package org.example.lab01.service;

import org.example.lab01.model.Location;

import java.util.List;

public interface LocationService {
    List<Location> listAll();
    Location findById(Long id);
    void deleteById(Long id);
    Location create(String name, String continent);
    Location update(Long id, String name, String continent);
}
