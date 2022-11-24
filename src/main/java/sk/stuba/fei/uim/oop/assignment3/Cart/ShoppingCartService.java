package sk.stuba.fei.uim.oop.assignment3.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.Cart.Interfaces.IShoppingCartRepository;
import sk.stuba.fei.uim.oop.assignment3.Cart.Interfaces.IShoppingCartService;
import sk.stuba.fei.uim.oop.assignment3.Product.Interfaces.ProductRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class ShoppingCartService implements IShoppingCartService {

    private final IShoppingCartRepository repository;

    @Autowired
    public ShoppingCartService(IShoppingCartRepository repository) {
        this.repository = repository;
    }

    @Override
    public ShoppingCart getById(long id) {
        return this.repository.findById(id);
    }

    @Override
    public ShoppingCart createShoppingCart() {
        ShoppingCart cart = new ShoppingCart();
        return repository.save(cart);
    }

    @Override
    public void delete(long id) {
        this.repository.delete(this.repository.findById(id));
    }

    @Override
    public ShoppingCart addToCart(long id, ShoppingCartRequest request, ProductRepository productRepository) {
        productRepository.findById(request.getProductId()).setAmount(productRepository.findById(request.getProductId()).getAmount() - request.getAmount());

        ShoppingCart cart =this.repository.findById(id);

        return this.repository.save(cart);
    }

    @Override
    public String payForCart(long id, ProductRepository productRepository) {
            ShoppingCart cart = this.repository.findById(id);
            cart.setPayed(true);

            double sum = 0;

            for (int i = 0;i<cart.getShoppingList().size();i++){

                sum += productRepository.findById(this.repository.findById(id).getShoppingList().get(i).getProductId()).getPrice() * this.repository.findById(id).getShoppingList().get(i).getAmount();

            }


            this.repository.save(cart);
        return Double.toString(sum);
    }


}
