package com.nimoh.commercetoy.order.repository;

import com.nimoh.commercetoy.address.domain.Address;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class AddressRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Address address) {
        em.persist(address);
    }
}
