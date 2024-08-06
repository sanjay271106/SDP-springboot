package com.sanjay.realestate.service;


import com.sanjay.realestate.model.Review;
import com.sanjay.realestate.repo.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Review> getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId);
    }

    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review updateReview(Long reviewId, Review reviewDetails) {
        return reviewRepository.findById(reviewId).map(review -> {
                    review.setTitle(reviewDetails.getTitle());
                    review.setContent(reviewDetails.getContent());
                    review.setRating(reviewDetails.getRating());
                    review.setUserId(reviewDetails.getUserId());
                    review.setDate(reviewDetails.getDate());
                    return reviewRepository.save(review);
                })
                .orElseThrow(() -> new RuntimeException("Review not found"));
    }

    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}

