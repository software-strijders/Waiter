package nl.softwarestrijders.waiter.product.core.port.data;

import nl.softwarestrijders.waiter.product.core.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ProductRepository extends MongoRepository<Product, UUID> {
    boolean existsByPriceAndNameAndDescription(double price, String name, String description);
    void deleteById(UUID id);
}
