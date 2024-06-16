package org.example.lab01.listeners;


import org.example.lab01.model.events.CourseApplyingEvent;
import org.example.lab01.service.CourseService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class AccommodationEventHandlers {

    private final CourseService courseService;

    public AccommodationEventHandlers(CourseService courseService) {
        this.courseService = courseService;
    }

    @EventListener
    public void booked(CourseApplyingEvent event) {
        this.courseService.book();
    }

}
