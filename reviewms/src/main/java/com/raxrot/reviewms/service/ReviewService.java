package com.raxrot.reviewms.service;


import com.raxrot.reviewms.model.Review;

import java.util.List;

public interface ReviewService {
    Review createReview(Long companyId, Review review);
    List<Review> getAllReviews(Long companyId);
    Review getReviewById(Long id);

    Review updateReview(Long id, Review review);

    void deleteReview(Long id);
}
