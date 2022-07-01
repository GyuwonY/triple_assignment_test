package com.triple.triple_assignment_test.service;

import com.triple.triple_assignment_test.dto.EventPointResponseDto;
import com.triple.triple_assignment_test.dto.ReviewEventActionEnum;
import com.triple.triple_assignment_test.dto.ReviewEventDto;
import com.triple.triple_assignment_test.dto.ReviewEventRequestDto;
import com.triple.triple_assignment_test.model.ReviewEvent;
import com.triple.triple_assignment_test.repository.ReviewEventRepository;
import com.triple.triple_assignment_test.util.ReviewEventUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReviewEventService {
    private final ReviewEventRepository reviewEventRepository;
    private final ReviewEventUtil reviewEventUtil;

    @Autowired
    public ReviewEventService(ReviewEventRepository reviewEventRepository, ReviewEventUtil reviewEventUtil){
        this.reviewEventRepository = reviewEventRepository;
        this.reviewEventUtil = reviewEventUtil;
    }

    public void reviewEvent(ReviewEventRequestDto reviewRequestDto){
        ReviewEventDto reviewEventDto = new ReviewEventDto(reviewRequestDto);

        if(reviewEventDto.getAction().equals(ReviewEventActionEnum.ADD)){
            reviewEventUtil.eventAdd(reviewEventDto);

        }else if(reviewEventDto.getAction().equals(ReviewEventActionEnum.MOD)){
            reviewEventUtil.eventMod(reviewEventDto, reviewEventRepository.findFirstByReviewIdAndActionOrderByCreatedAtDesc(reviewEventDto.getReviewId(), ReviewEventActionEnum.ADD));

        }else if(reviewEventDto.getAction().equals(ReviewEventActionEnum.DELETE)){
            reviewEventUtil.eventDelete(reviewEventDto, reviewEventRepository.findFirstByReviewIdAndActionOrderByCreatedAtDesc(reviewEventDto.getReviewId(), ReviewEventActionEnum.ADD));

        }

        reviewEventRepository.save(new ReviewEvent(reviewEventDto));
    }


    public ResponseEntity<EventPointResponseDto> getEventPoint(String userId) {

        return new ResponseEntity<>(new EventPointResponseDto(userId, reviewEventRepository.sumPointByuserId(userId)), HttpStatus.OK);
    }
}
