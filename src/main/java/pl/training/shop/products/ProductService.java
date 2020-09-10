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
    public PagedResult<Product> findByName(String name, int pageNumber, int pageSize) {
        log.info("reading from database...");
        Page<Product> byNameContaining = productRepository.findByNameContaining(name, PageRequest.of(pageNumber, pageSize));
        return new PagedResult<>(byNameContaining.getContent(), pageNumber, byNameContaining.getTotalPages());
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
    }

    public PagedResult<Product> getAll(int pageNumber, int pageSize) {
        Page<Product> productPage = productRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return new PagedResult<>(productPage.getContent(), pageNumber, productPage.getTotalPages());
    }
}
