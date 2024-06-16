package org.example.lab01.model.events;

import lombok.Getter;
import org.example.lab01.model.Course;
import org.springframework.context.ApplicationEvent;


@Getter
public class CourseApplyingEvent extends ApplicationEvent {

    public CourseApplyingEvent(Course course) {
        super(course);
    }
}
