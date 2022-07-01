package com.triple.triple_assignment_test.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewEventDto {

    private ReviewEventActionEnum action;
    private String userId;
    private String placeId;
    private String reviewId;
    private boolean content;
    private boolean attachedPhoto;
    private boolean isFirst;
    private int point;

    public ReviewEventDto(ReviewEventRequestDto reviewEventRequestDto){
        this.action = reviewEventRequestDto.getAction();
        this.userId = reviewEventRequestDto.getUserId();
        this.placeId = reviewEventRequestDto.getPlaceId();
        this.reviewId = reviewEventRequestDto.getReviewId();
        this.content = reviewEventRequestDto.getContent().length() != 0;
        this.attachedPhoto = reviewEventRequestDto.getAttachedPhotoIds().size() > 0;
    }

    public void plusPoint(){
        this.point++;
    }

    public void minusPoint(){
        this.point--;
    }


}
