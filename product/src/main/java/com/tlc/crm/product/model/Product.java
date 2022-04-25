package com.tlc.crm.product.model;

import com.tlc.crm.product.validator.Size;
import com.tlc.validator.TlcModel;
import com.tlc.validator.type.Group.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Handles product details.
 *
 * @author AswiniN
 */
public class Product implements TlcModel {

    private Long id;

    @NotBlank(groups = {Create.class, Update.class})
    private String productName;

    @NotNull(groups = {Create.class, Update.class})
    private Integer price;

    @Size(groups = {Create.class, Update.class})
    private String size;

    @NotNull(groups = {Create.class, Update.class})
    private String quantity;

    public Product() {
    }

    public Product(Long id, String productName, Integer price, String size, String quantity) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.size = size;
        this.quantity = quantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String toString() {
        return String.format("%s %s %s %s %s ", id, productName, price, size, quantity);
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public Long orgId() {
        return null;
    }

    @Override
    public Object identity() {
        return null;
    }
}
