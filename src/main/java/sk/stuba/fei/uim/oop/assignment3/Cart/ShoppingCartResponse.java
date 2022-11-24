package sk.stuba.fei.uim.oop.assignment3.Cart;

import lombok.Getter;

import java.util.List;
@Getter
public class ShoppingCartResponse {
    private final Long id;
    private final boolean payed;
    private final List<ItemInCart> shoppingList;

    public ShoppingCartResponse(ShoppingCart cart){
        this.id = cart.getId();
        this.payed = cart.isPayed();
        this.shoppingList = cart.getShoppingList();
    }

}
