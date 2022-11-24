package sk.stuba.fei.uim.oop.assignment3.Cart.Interfaces;

import sk.stuba.fei.uim.oop.assignment3.Cart.ShoppingCart;
import sk.stuba.fei.uim.oop.assignment3.Cart.ShoppingCartRequest;
import sk.stuba.fei.uim.oop.assignment3.Product.Interfaces.ProductRepository;

public interface IShoppingCartService {

    ShoppingCart getById(long id);

    ShoppingCart createShoppingCart();

    void delete(long id);

    ShoppingCart addToCart(long id, ShoppingCartRequest request, ProductRepository repository);

    String payForCart(long id, ProductRepository repository);
}
