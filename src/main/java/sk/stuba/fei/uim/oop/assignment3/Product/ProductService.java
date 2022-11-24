package sk.stuba.fei.uim.oop.assignment3.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import sk.stuba.fei.uim.oop.assignment3.Product.Interfaces.IProductService;
import sk.stuba.fei.uim.oop.assignment3.Product.Interfaces.ProductRepository;

import java.util.List;

@Service
public class ProductService implements IProductService {


    private final ProductRepository repository;



    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Product> getAll(){

        return this.repository.findAll();

    }

    @Override
    public Product getByID(long id) {
        return this.repository.findById(id);
    }

    @Override
    public Product create(ProductRequest request) {

        Product newProduct = new Product();
        newProduct.setName(request.getName());
        newProduct.setDescription(request.getDescription());
        newProduct.setPrice(request.getPrice());
        newProduct.setUnit(request.getUnit());
        newProduct.setAmount(request.getAmount());

        return this.repository.save(newProduct);
    }

    @Override
    public void update(ProductRequest request, long id){

        Product newProduct = this.repository.findById(id);
        System.out.println(this.repository.findById(id).getName());

        if (request.getName()!=null){
            newProduct.setName(request.getName());
        }
        if (request.getDescription()!=null) {
            newProduct.setDescription(request.getDescription());
        }

        this.repository.save(newProduct);
    }

    @Override
    public void delete(long id){
        this.repository.delete(this.repository.findById(id));
    }

    @Override
    public void increaseAmount(long id, int amount){

        Product newProduct = this.repository.findById(id);

        newProduct.setAmount(newProduct.getAmount()+amount);

        this.repository.save(newProduct);
    }
}
