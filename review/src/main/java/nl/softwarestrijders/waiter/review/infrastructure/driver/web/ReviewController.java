package nl.softwarestrijders.waiter.review.infrastructure.driver.web;

import nl.softwarestrijders.waiter.review.core.application.QueryHandler;
import nl.softwarestrijders.waiter.review.core.application.query.FindAllByCustomerId;
import nl.softwarestrijders.waiter.review.core.application.query.FindReviewById;
import nl.softwarestrijders.waiter.review.core.application.query.ListAll;
import nl.softwarestrijders.waiter.review.core.application.query.concept.FindAllByDeliveryId;
import nl.softwarestrijders.waiter.review.core.application.query.concept.FindAllByProductId;
import nl.softwarestrijders.waiter.review.core.domain.Review;
import nl.softwarestrijders.waiter.review.infrastructure.driver.web.dto.ReviewDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/review")
public class ReviewController {
    private final QueryHandler queryHandler;

    public ReviewController(QueryHandler queryHandler) {
        this.queryHandler = queryHandler;
    }

    @GetMapping("/{id}")
    public ReviewDto findByReview(@PathVariable UUID id) {
        return this.toDto(this.queryHandler.handle(new FindReviewById(id)));
    }

    @GetMapping("/customer/{id}")
    public List<ReviewDto> findByCustomer(@PathVariable UUID id) {
        return this.toDto(this.queryHandler.handle(new FindAllByCustomerId(id)));
    }

    @GetMapping("/product/{id}")
    public List<ReviewDto> findByProduct(@PathVariable UUID id) {
        return this.toDto(this.queryHandler.handle(new FindAllByProductId(id)));
    }

    @GetMapping("/delivery/{id}")
    public List<ReviewDto> findByDelivery(@PathVariable UUID id) {
        return this.toDto(this.queryHandler.handle(new FindAllByDeliveryId(id)));
    }

    @GetMapping
    public List<ReviewDto> listAll(@RequestParam String direction, @RequestParam String sort) {
        return this.toDto(this.queryHandler.handle(new ListAll(direction, sort)));
    }

    private List<ReviewDto> toDto(List<Review> reviews) {
        return reviews.stream().map(this::toDto).toList();
    }

    private ReviewDto toDto(Review review) {
        return new ReviewDto(
                review.getId(),
                review.getConceptId(),
                review.getCustomerId(),
                review.getType(),
                review.getTitle(),
                review.getDescription(),
                review.getRating().value()
        );
    }
}