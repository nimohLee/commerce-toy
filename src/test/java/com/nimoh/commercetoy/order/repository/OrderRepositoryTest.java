package com.nimoh.commercetoy.order.repository;

import com.nimoh.commercetoy.address.domain.Address;
import com.nimoh.commercetoy.order.domain.Orders;
import com.nimoh.commercetoy.order.dto.OrderUpdateRequestDto;
import com.nimoh.commercetoy.product.domain.Category;
import com.nimoh.commercetoy.product.domain.Product;
import com.nimoh.commercetoy.product.repository.CategoryRepository;
import com.nimoh.commercetoy.product.repository.ProductRepository;
import com.nimoh.commercetoy.user.domain.User;
import com.nimoh.commercetoy.user.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({OrderRepository.class, UserRepository.class, AddressRepository.class, ProductRepository.class, CategoryRepository.class})
@Slf4j
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PersistenceContext
    private EntityManager em;

    @Test
    void saveAndFindById() {

        User user = User.builder().name("nimoh").addressList(new ArrayList<>()).build();

        userRepository.save(user);

        Address address = Address.builder()
                .city("busan")
                .street("niro")
                .zipCode("00000")
                .build();

        addressRepository.save(address);

        user.addAddress(address);

        Product product = Product.builder()
                .name("food")
                .stock(30)
                .sales(10)
                .price(10000)
                .category(new Category("food"))
                .build();

        Orders order = Orders.builder()
                .user(user)
                .product(product)
                .amount(10)
                .address(user.getMainAddress())
                .build();

        Orders savedOrder = orderRepository.save(order);

        Orders findOrder = orderRepository.findById(savedOrder.getId()).get();
        em.flush();
        em.clear();

        log.info(findOrder.getProduct().toString());

    }

    @Test
    void findAll() {
        for (int i = 0; i < 10; i++) {
            User user = User.builder()
                    .name("user" + i)
                    .build();
            userRepository.save(user);

            Product product = Product.builder()
                    .name("item" + i)
                    .build();

            productRepository.save(product);

            Orders orders = Orders.builder()
                    .user(user)
                    .product(product)
                    .build();
            orderRepository.save(orders);
        }

        em.flush();
        em.clear();
        List<Orders> findOrderList = orderRepository.findAll();
        assertThat(findOrderList.get(0).getUser().getName()).isEqualTo("user0");
        assertThat(findOrderList.get(9).getUser().getName()).isEqualTo("user9");
        assertThat(findOrderList.get(0).getProduct().getName()).isEqualTo("item0");
        assertThat(findOrderList.get(9).getProduct().getName()).isEqualTo("item9");
        assertThat(findOrderList.size()).isEqualTo(10);
    }

    @Test
    void update() {
        Orders order = createOrder();

        em.flush();
        em.clear();

        Product product = Product.builder()
                .name("shoes")
                .build();

        productRepository.save(product);

        orderRepository.update(order.getId(), OrderUpdateRequestDto.builder().product(product).build());

        em.flush();
        em.clear();

        Orders findOrder = orderRepository.findById(order.getId()).get();

        assertThat(findOrder.getProduct().getName()).isEqualTo("shoes");

    }

    @Test
    void delete() {
        Orders order = createOrder();

        orderRepository.delete(order.getId());

        assertThatThrownBy(() -> orderRepository.findById(order.getId())).isInstanceOf(NullPointerException.class);

    }

    @Test
    void findByUser() {
        Orders order = createOrder();
        em.flush();
        em.clear();
        User user = order.getUser();

        List<Orders> findOrderList = orderRepository.findByUser(user);

        assertThat(findOrderList.size()).isEqualTo(1);
        assertThat(findOrderList.get(0).getUser().getId()).isEqualTo(user.getId());

    }

    private Orders createOrder() {
        User user = User.builder().name("nimoh").addressList(new ArrayList<>()).build();

        userRepository.save(user);

        Address address = Address.builder()
                .city("busan")
                .street("niro")
                .zipCode("00000")
                .build();

        addressRepository.save(address);

        user.addAddress(address);


        Category category = new Category("food");

        categoryRepository.save(category);

        Product product = Product.builder()
                .name("food")
                .stock(30)
                .sales(10)
                .price(10000)
                .category(category)
                .build();

        productRepository.save(product);

        Orders order = Orders.builder()
                .user(user)
                .product(product)
                .amount(10)
                .address(user.getMainAddress())
                .build();

        return orderRepository.save(order);
    }
}