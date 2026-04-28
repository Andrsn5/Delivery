package dev.andrsn.delivery.common.model;

import lombok.Getter;

@Getter
public enum ProductType {
    BOOK("Книга", new Size(20.0, 3.0, 13.0)),
    PHONE("Смартфон", new Size(15.0, 1.0, 7.0)),
    TABLET("Планшет", new Size(25.0, 0.7, 17.0)),
    LAPTOP("Ноутбук", new Size(35.0, 2.0, 24.0));

    private final String ruName;
    private final Size size;

    ProductType(String ruName, Size size) {
        this.ruName = ruName;
        this.size = size;
    }
}
