package com.raxrot.reviewms.controller;

import com.raxrot.reviewms.model.Review;
import com.raxrot.reviewms.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestParam Long companyId, @RequestBody Review review) {
        return new ResponseEntity<>(reviewService.createReview(companyId, review), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Review>> findAllByCompanyId(@RequestParam Long companyId) {
        return ResponseEntity.ok(reviewService.getAllReviews(companyId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> findById(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id,@RequestBody Review review) {
        return ResponseEntity.ok(reviewService.updateReview(id,review));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
