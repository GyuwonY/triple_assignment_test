package com.triple.triple_assignment_test.repository;

import com.triple.triple_assignment_test.dto.ReviewEventActionEnum;
import com.triple.triple_assignment_test.model.ReviewEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewEventRepository extends JpaRepository<ReviewEvent, Long> {

    boolean existsByPlaceIdAndIsFirstTrue(String placeId);

    ReviewEvent findFirstByReviewIdAndActionOrderByCreatedAtDesc(String reviewId, ReviewEventActionEnum actionEnum);

    boolean existsByReviewIdNotAndAction(String reviewId, ReviewEventActionEnum actionEnum);

    @Query("select sum(m.point) from ReviewEvent m where m.userID=?1")
    Long sumPointByuserId(String userId);
}
