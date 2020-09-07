package pl.training.shop.products;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.training.shop.common.PagedResult;
import pl.training.shop.common.retry.Retry;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Retry
    public Product add(Product product) {
        return productRepository.save(product);
    }

    @Cacheable("products")
    public List<Product> findByName(String name){
        return productRepository.findByNameContaining(name);
    }

    public PagedResult<Product> getAll(int pageNumber, int pageSize) {
        Page<Product> productPage = productRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return new PagedResult<>(productPage.getContent(), pageNumber, productPage.getTotalPages());
    }
}
