package sk.stuba.fei.uim.oop.assignment3.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.Cart.Interfaces.IShoppingCartService;
import sk.stuba.fei.uim.oop.assignment3.Product.Interfaces.ProductRepository;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    private IShoppingCartService service;

    @Autowired
    private ProductRepository repository;

    @PostMapping
    public ResponseEntity<ShoppingCartResponse> createShoppingCart(){
        return new ResponseEntity<>(new ShoppingCartResponse(this.service.createShoppingCart()), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartResponse> getShoppingCart(@PathVariable("id") long id){

        if (this.service.getById(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ShoppingCartResponse(this.service.getById(id)),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id){
        if (this.service.getById(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/add")
    public ResponseEntity<ShoppingCartResponse> addToCart(@PathVariable("id") long id ,@RequestBody ShoppingCartRequest request){

        if (this.service.getById(id) == null || repository.findById(request.getProductId()) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (this.service.getById(id).isPayed() || repository.findById(request.getProductId()).getAmount() < request.getAmount()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity<>(new ShoppingCartResponse(this.service.addToCart(id,request,repository)),HttpStatus.OK);
    }

    @GetMapping("/{id}/pay")

    public ResponseEntity<String> payForCart(@PathVariable("id") long id){

        if (this.service.getById(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (this.service.getById(id).isPayed()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(this.service.payForCart(id,repository),HttpStatus.OK);
    }


}
