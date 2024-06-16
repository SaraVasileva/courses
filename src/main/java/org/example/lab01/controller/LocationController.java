
package org.example.lab01.controller;

import org.example.lab01.model.Location;
import org.example.lab01.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public String getLocations(Model model) {
        List<Location> locations = this.locationService.listAll();
        model.addAttribute("locations", locations);
        return "locations";
    }

    @PostMapping("/delete/{id}")
    public String deleteLocation(@PathVariable Long id) {
        if (locationService.findById(id) != null) {
            this.locationService.deleteById(id);
        }
        return "redirect:/locations";
    }
}
