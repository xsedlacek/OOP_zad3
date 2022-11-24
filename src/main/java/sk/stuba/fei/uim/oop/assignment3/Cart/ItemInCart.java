package sk.stuba.fei.uim.oop.assignment3.Cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ItemInCart {

    @ManyToOne
    private ShoppingCart cart;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productId;
    private int amount;
}
