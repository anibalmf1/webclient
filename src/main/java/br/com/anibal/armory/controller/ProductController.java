package br.com.anibal.armory.controller;

import br.com.anibal.armory.model.Product;
import br.com.anibal.armory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(ProductController.PRODUCT_URL)
public class ProductController {

    public static final String PRODUCT_URL = "/product";
    public static final String GET_PRODUCT_URL = "/{id}";
    public static final String GET_PRODUCT_WITH_PRICE_URL = "/{id}/price";

    @Autowired
    private ProductService service;

    @GetMapping(GET_PRODUCT_URL)
    public ResponseEntity<?> getProduct(@PathVariable Integer id) {
        Optional<Product> product = service.getProduct(id);

        return product.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(GET_PRODUCT_WITH_PRICE_URL)
    public ResponseEntity<?> getProductWithPrice(@PathVariable Integer id) {
        Optional<Product> product = service.getProductWithPrice(id);

        return product.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody @Valid Product product) {
        Optional<Product> created = service.saveProduct(product);

        return created.map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
    }
}