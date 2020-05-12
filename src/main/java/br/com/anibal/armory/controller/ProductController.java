package br.com.anibal.armory.controller;

import br.com.anibal.armory.model.Price;
import br.com.anibal.armory.model.Product;
import br.com.anibal.armory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        Product product = service.getProduct(id);

        if (product == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping(GET_PRODUCT_WITH_PRICE_URL)
    public  ResponseEntity<?> getProductWithPrice(@PathVariable Integer id) {
        Product product = service.getProductWithPrice(id);

        if (product == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
