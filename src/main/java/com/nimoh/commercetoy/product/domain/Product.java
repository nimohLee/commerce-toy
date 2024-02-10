package com.nimoh.commercetoy.product.domain;

import com.nimoh.commercetoy.base.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    private int price;

    private int stock;

    private int sales;

    @Builder
    public Product(String name, Category category, int price, int stock, int sales) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.sales = sales;
    }

    public void decreaseStock(int count) {
        this.stock = this.stock - count;
    }

    public void increaseSales(int count) {
        this.sales = this.sales + count;
    }
}
