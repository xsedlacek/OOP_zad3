package sk.stuba.fei.uim.oop.assignment3.Product;

import lombok.Getter;


@Getter
public class ProductResponse {
    private final String name;
    private final String description;
    private final int amount;
    private final String unit;
    private final float price;
    private final Long id;


    public ProductResponse(Product p ) {
        this.name = p.getName();
        this.description = p.getDescription();
        this.amount = p.getAmount();
        this.unit = p.getUnit();
        this.price = p.getPrice();
        this.id = p.getId();
    }

}
