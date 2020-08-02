package pl.training.shop.products;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import pl.training.shop.common.PagedResult;

import java.util.List;

@RequiredArgsConstructor
public class HibernateProductRepository implements ProductRepository {
    private final SessionFactory sessionFactory;

    @Override
    public Product save(Product product) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(product);
        product.setId(id);
        return product;
    }

    @Override
    public PagedResult<Product> findAll(int pageNumber, int pageSize) {
        List<Product> products = sessionFactory.getCurrentSession()
                .createNamedQuery(Product.SELECT_PRODUCT, Product.class)
                .setFirstResult(pageNumber * pageSize)
                .setMaxResults(pageSize)
                .getResultList();

        return new PagedResult<>(products, pageNumber, -1); //-1 -> unknown total pages; should get total from db and divide pageSize
    }
}
