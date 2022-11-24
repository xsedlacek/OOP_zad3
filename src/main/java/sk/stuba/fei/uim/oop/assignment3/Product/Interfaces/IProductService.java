package sk.stuba.fei.uim.oop.assignment3.Product.Interfaces;


import sk.stuba.fei.uim.oop.assignment3.Product.Product;
import sk.stuba.fei.uim.oop.assignment3.Product.ProductRequest;

import java.util.List;

public interface IProductService {

    Product create(ProductRequest request);

    List<Product> getAll();

    Product getByID(long id);

    void update(ProductRequest request, long id);

    void delete(long id);

    void increaseAmount(long id,int amount);
}
