package sk.stuba.fei.uim.oop.assignment3.Cart.Interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.stuba.fei.uim.oop.assignment3.Cart.ShoppingCart;

@Repository
public interface IShoppingCartRepository extends CrudRepository<ShoppingCart,Long> {

    ShoppingCart findById(long id);
}
