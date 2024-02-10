package com.nimoh.commercetoy.product.repository;

import com.nimoh.commercetoy.product.domain.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository {

    @PersistenceContext
    private EntityManager em;

    public Category save(Category category) {
        em.persist(category);
        return category;
    }
}
