package com.nimoh.commercetoy.product.repository;

import com.nimoh.commercetoy.product.domain.Category;
import com.nimoh.commercetoy.product.domain.Product;
import com.nimoh.commercetoy.product.dto.ProductUpdateRequestDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Import({ProductRepository.class, CategoryRepository.class})
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PersistenceContext
    private EntityManager em;

    @Test
    void saveAndFindById() {
        Product product = Product.builder()
                .name("nimoh")
                .build();

        Product savedProduct = productRepository.save(product);

        Product findProduct = productRepository.findById(savedProduct.getId()).get();

        assertThat(findProduct.getName()).isEqualTo(product.getName());
    }

    @Test
    void findAll() {
        for (int i = 0; i < 10; i++) {
            Product product = Product.builder()
                    .name("nimoh" + i)
                    .build();
            productRepository.save(product);
        }

        List<Product> findProductList = productRepository.findAll();
        assertThat(findProductList.get(0).getName()).isEqualTo("nimoh0");
        assertThat(findProductList.get(9).getName()).isEqualTo("nimoh9");
        assertThat(findProductList.size()).isEqualTo(10);
    }

    @Test
    void update() {
        Category c = new Category("shoes");

        categoryRepository.save(c);

        Product product = Product.builder()
                .name("nike")
                .sales(10)
                .category(c)
                .stock(5)
                .price(10000)
                .build();

        Product savedProduct = productRepository.save(product);

        // 여기서 flush 안 해주면, 1차캐시에서 가져올 뿐임.
        em.flush();
        em.clear();

        ProductUpdateRequestDto dto = new ProductUpdateRequestDto();
        dto.setName("modi");
        dto.setSales(11);
        dto.setStock(4);
        dto.setPrice(12000);

        productRepository.update(savedProduct.getId(), dto);

        Product findProduct = productRepository.findById(savedProduct.getId()).get();

        assertThat(findProduct.getName()).isEqualTo("modi");
        assertThat(findProduct.getSales()).isEqualTo(11);
        assertThat(findProduct.getStock()).isEqualTo(4);
        assertThat(findProduct.getPrice()).isEqualTo(12000);
    }

    @Test
    void delete() {
        Product product = Product.builder()
                .name("nimoh" )
                .build();
        Product savedProduct = productRepository.save(product);

        productRepository.delete(savedProduct.getId());

        assertThatThrownBy(() -> productRepository.findById(savedProduct.getId())).isInstanceOf(NullPointerException.class);

    }

    @Test
    void findAllByCategoryId() {
        long categoryId = 0;

        Category shoes = new Category("shoes");
        Category clothes = new Category("shoes");

        categoryRepository.save(shoes);
        categoryRepository.save(clothes);

        for (int i = 0; i < 10; i++) {
            Category category;
            if (i < 5) {
                category = shoes;
            } else {
                category = clothes;
            }

            Product product = Product.builder()
                    .name("item" + i)
                    .category(category)
                    .build();
            productRepository.save(product);
        }

        em.flush();
        em.clear();

        List<Product> productList = productRepository.findAllByCategory(shoes);

        assertThat(productList.size()).isEqualTo(5);
        assertThat(productList.get(0).getCategory().getName()).isEqualTo(shoes.getName());
        assertThat(productList.get(4).getCategory().getName()).isEqualTo(shoes.getName());

    }

}