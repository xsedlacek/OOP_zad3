package sk.stuba.fei.uim.oop.assignment3.Product;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.Product.Interfaces.IProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService service;


    @GetMapping
    public List<ProductResponse> getAll() {

        return this.service.getAll().stream().map(ProductResponse::new).collect(Collectors.toList());
    }


    @PostMapping()
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest request) {

        return new ResponseEntity<>(new ProductResponse(this.service.create(request)),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<ProductResponse> getByID(@PathVariable("id") long id){
        if (this.service.getByID(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ProductResponse(this.service.getByID(id)),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("id") long id,@RequestBody ProductRequest request){
        if (this.service.getByID(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.service.update(request,id);
        return new ResponseEntity<>(new ProductResponse(this.service.getByID(id)),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id){
        if (this.service.getByID(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/amount")
    public ResponseEntity<JSONObject> getAmount(@PathVariable("id") long id){
        if (this.service.getByID(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        JSONObject json = new JSONObject();
        json.put("amount",this.service.getByID(id).getAmount());
        return new ResponseEntity<>(json,HttpStatus.OK);
    }

    @PostMapping("/{id}/amount")
    public ResponseEntity<JSONObject> increaseAmount(@PathVariable("id") long id, @RequestBody ProductRequest request){

        if (this.service.getByID(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        service.increaseAmount(id, request.getAmount());
        JSONObject json = new JSONObject();
        json.put("amount",this.service.getByID(id).getAmount());
        return new ResponseEntity<>(json,HttpStatus.OK);
    }

}
