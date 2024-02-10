package com.nimoh.commercetoy.product.repository;

import com.nimoh.commercetoy.product.domain.Category;
import com.nimoh.commercetoy.product.domain.Product;
import com.nimoh.commercetoy.product.dto.ProductUpdateRequestDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    @PersistenceContext
    private final EntityManager em;

    public Product save(Product product) {
        em.persist(product);

        return product;
    }

    public Optional<Product> findById(Long id) {

        Product product = em.find(Product.class, id);

        return Optional.of(product);
    }

    public List<Product> findAll() {
        String sql = "select p from Product p";
        return em.createQuery(sql, Product.class).getResultList();
    }

    public void update(Long productId, ProductUpdateRequestDto dto) {
        Product findProduct = em.find(Product.class, productId);
        findProduct.setName(dto.getName());
        findProduct.setSales(dto.getSales());
        findProduct.setPrice(dto.getPrice());
        findProduct.setStock(dto.getStock());

    }

    public void delete(Long productId) {
        Product findProduct = em.find(Product.class, productId);
        em.remove(findProduct);
    }

    public List<Product> findAllByCategory(Category category) {
        String sql = "select p from Product p where p.category = :category";

        return em.createQuery(sql, Product.class)
                .setParameter("category", category)
                .getResultList();

    }
}
