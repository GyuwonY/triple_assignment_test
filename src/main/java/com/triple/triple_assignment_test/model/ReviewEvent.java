package com.triple.triple_assignment_test.model;

import com.triple.triple_assignment_test.dto.ReviewEventActionEnum;
import com.triple.triple_assignment_test.dto.ReviewEventDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_event", indexes = {
        @Index(name = "i_placeId", columnList = "placeId"),
        @Index(name = "i_reviewId", columnList = "reviewId"),
        @Index(name = "i_userId", columnList = "userID")
})
@EntityListeners(AuditingEntityListener.class)
public class ReviewEvent {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    @Column
    private Long eventID;

    @Column(nullable = false)
    private String userID;

    @Column(nullable = false)
    private String placeId;

    @Column(nullable = false)
    private String reviewId;

    @Column(nullable = false)
    private ReviewEventActionEnum action;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int point;

    @Column(nullable = false)
    private boolean content;

    @Column(nullable = false)
    private boolean attachedPhoto;

    @Column(nullable = false)
    private boolean isFirst;

    @CreatedDate
    private LocalDateTime createdAt;

    public ReviewEvent(ReviewEventDto reviewEventDto){
        this.userID = reviewEventDto.getUserId();
        this.placeId = reviewEventDto.getPlaceId();
        this.reviewId = reviewEventDto.getReviewId();
        this.action = reviewEventDto.getAction();
        this.point = reviewEventDto.getPoint();
        this.content = reviewEventDto.isContent();
        this.attachedPhoto = reviewEventDto.isAttachedPhoto();
        this.isFirst = reviewEventDto.isFirst();
    }

    public void deleteReview(){
        this.isFirst = false;
    }

    public void deleteContent(){
        this.content = false;
    }

    public void deletePhoto(){
        this.attachedPhoto = false;
    }

    public void insertContent() {
        this.content = true;
    }

    public void insertPhoto() {
        this.attachedPhoto = true;
    }
}


