package com.triple.triple_assignment_test.controller;

import com.triple.triple_assignment_test.dto.EventPointResponseDto;
import com.triple.triple_assignment_test.dto.ReviewEventRequestDto;
import com.triple.triple_assignment_test.service.ReviewEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewEventController {
    private final ReviewEventService reviewEventService;

    @Autowired
    public ReviewEventController(ReviewEventService reviewEventService) {
        this.reviewEventService = reviewEventService;
    }

    @PostMapping("/events")
    public void reviewEvent(@RequestBody ReviewEventRequestDto reviewRequestDto){
        reviewEventService.reviewEvent(reviewRequestDto);
    }

    @GetMapping("/events/{userId}")
    public ResponseEntity<EventPointResponseDto> getEventPoint(@PathVariable String userId){
        return reviewEventService.getEventPoint(userId);
    }
}
