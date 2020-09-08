package pl.training.shop.products;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.training.shop.common.PagedResult;
import pl.training.shop.common.retry.Retry;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Log
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    @CacheEvict("products")
    @Retry
    public Product add(Product product) {
        return productRepository.save(product);
    }

    @Cacheable("products")
    public List<Product> findByName(String name) {
        log.info("reading from database...");
        return productRepository.findByNameContaining(name);
    }

    public PagedResult<Product> getAll(int pageNumber, int pageSize) {
        Page<Product> productPage = productRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return new PagedResult<>(productPage.getContent(), pageNumber, productPage.getTotalPages());
    }
}
