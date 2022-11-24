package sk.stuba.fei.uim.oop.assignment3.Product;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Getter
@Setter
public class Product {
    private String name;
    private String description;
    private int amount;
    private String unit;
    private float price;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;



}
