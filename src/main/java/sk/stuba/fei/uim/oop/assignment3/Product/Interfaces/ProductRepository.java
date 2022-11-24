package sk.stuba.fei.uim.oop.assignment3.Product.Interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.stuba.fei.uim.oop.assignment3.Product.Product;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {

    List<Product> findAll();

    Product findById(long id);


}
