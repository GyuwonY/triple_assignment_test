package com.triple.triple_assignment_test.util;

import com.triple.triple_assignment_test.dto.ReviewEventActionEnum;
import com.triple.triple_assignment_test.dto.ReviewEventDto;
import com.triple.triple_assignment_test.model.ReviewEvent;
import com.triple.triple_assignment_test.repository.ReviewEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewEventUtil {
    private final ReviewEventRepository reviewEventRepository;

    @Autowired
    public ReviewEventUtil(ReviewEventRepository reviewEventRepository){
        this.reviewEventRepository = reviewEventRepository;
    }

    public void eventAdd(ReviewEventDto reviewEventDto){
        if(reviewEventDto.isContent()) {
            reviewEventDto.plusPoint();
        }
        if(reviewEventDto.isAttachedPhoto()){
            reviewEventDto.plusPoint();
        }
        if(!reviewEventRepository.existsByPlaceIdAndIsFirstTrue(reviewEventDto.getPlaceId())){
            reviewEventDto.setFirst(true);
            reviewEventDto.plusPoint();
        }
    }

    public void eventMod(ReviewEventDto reviewEventDto, ReviewEvent reviewEvent){
        if( (!reviewEventDto.isContent()) && reviewEvent.isContent()){
            reviewEvent.deleteContent();
            reviewEventDto.minusPoint();
        }else if(reviewEventDto.isContent() && (!reviewEvent.isContent())){
            reviewEvent.insertContent();
            reviewEventDto.plusPoint();
        }

        if( (!reviewEventDto.isAttachedPhoto()) && reviewEvent.isAttachedPhoto()){
            reviewEvent.deletePhoto();
            reviewEventDto.minusPoint();
        }else if(reviewEventDto.isAttachedPhoto() && (!reviewEvent.isAttachedPhoto())){
            reviewEvent.insertPhoto();
            reviewEventDto.plusPoint();
        }

    }

    public void eventDelete(ReviewEventDto reviewEventDto, ReviewEvent reviewEvent){
        if(reviewEvent.isContent()){
            reviewEventDto.minusPoint();
        }
        if(reviewEvent.isAttachedPhoto()){
            reviewEventDto.minusPoint();
        }
        if(reviewEvent.isFirst()){
            reviewEventDto.minusPoint();

            if(!reviewEventRepository.existsByReviewIdNotAndAction(reviewEventDto.getReviewId(), ReviewEventActionEnum.ADD)) {
                reviewEvent.deleteReview();
            }
        }
    }
}
