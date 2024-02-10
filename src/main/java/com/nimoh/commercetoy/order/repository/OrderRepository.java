package com.nimoh.commercetoy.order.repository;

import com.nimoh.commercetoy.order.domain.Orders;
import com.nimoh.commercetoy.order.dto.OrderUpdateRequestDto;
import com.nimoh.commercetoy.user.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class OrderRepository {

    @PersistenceContext
    private EntityManager em;

    public Orders save(Orders orders) {
        em.persist(orders);

        return orders;
    }

    public Optional<Orders> findById(Long id) {

        Orders findOrders = em.find(Orders.class, id);

        return Optional.of(findOrders);
    }

    public List<Orders> findAll() {
        String sql = "select o from Orders o join fetch o.user u left join fetch o.product p";
        return em.createQuery(sql, Orders.class).getResultList();
    }

    public void update(Long orderId, OrderUpdateRequestDto dto) {
        Orders findOrders = em.find(Orders.class, orderId);
        findOrders.setProduct(dto.getProduct());
        findOrders.setAddress(dto.getAddress());
        findOrders.setTotalPrice(dto.getTotalPrice());
        findOrders.setAmount(dto.getAmount());
        findOrders.setOrderStatus(dto.getOrderStatus());

    }

    public void delete(Long orderId) {
        Orders findOrders = em.find(Orders.class, orderId);
        em.remove(findOrders);
    }

    public List<Orders> findByUser(User user) {
        String sql = "select o from Orders o where user = :user";
        return em.createQuery(sql, Orders.class)
                .setParameter("user", user)
                .getResultList();

    }
}
