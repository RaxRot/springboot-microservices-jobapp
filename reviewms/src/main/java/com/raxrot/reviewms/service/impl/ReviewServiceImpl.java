package com.raxrot.reviewms.service.impl;


import com.raxrot.reviewms.exception.ApiException;
import com.raxrot.reviewms.model.Review;
import com.raxrot.reviewms.repository.ReviewRepository;
import com.raxrot.reviewms.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    //private final CompanyService companyService;

    @Override
    public Review createReview(Long companyId, Review review) {
        review.setCompanyId(companyId);
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public Review getReviewById(Long id) {
        Review review=reviewRepository.findById(id)
                .orElseThrow(()->new ApiException("Review not found", HttpStatus.NOT_FOUND));
        return review;
    }

    @Override
    public Review updateReview(Long id, Review review) {
        Review reviewToUpdate=reviewRepository.findById(id)
                .orElseThrow(()->new ApiException("Review not found", HttpStatus.NOT_FOUND));
        reviewToUpdate.setTitle(review.getTitle());
        reviewToUpdate.setDescription(review.getDescription());
        reviewToUpdate.setRating(review.getRating());
        reviewToUpdate.setCompanyId(review.getCompanyId());
        Review updatedReview=reviewRepository.save(reviewToUpdate);
        return updatedReview;
    }

    @Override
    public void deleteReview(Long id) {
        Review review=reviewRepository.findById(id)
                .orElseThrow(()->new ApiException("Review not found", HttpStatus.NOT_FOUND));
        reviewRepository.delete(review);
    }
}
