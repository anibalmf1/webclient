package br.com.anibal.armory.service;

import br.com.anibal.armory.model.Price;
import br.com.anibal.armory.model.Product;
import br.com.anibal.armory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Optional;

@Service
public class ProductService {

    private final String GET_PRICE_URL = "/price/{product_id}";

    @Autowired
    private WebClient priceWebClient;

    @Autowired
    private ProductRepository repository;

    private Double getPrice(Integer productId) {
        try {
            Price price = priceWebClient
                    .get()
                    .uri(GET_PRICE_URL, productId)
                    .retrieve()
                    .bodyToMono(Price.class)
                    .block();
            return price.getPrice();
        } catch (WebClientResponseException e){
            return null;
        }
    }

    public Optional<Product> getProduct(Integer id) {
        return repository.findById(id);
    }

    public Optional<Product> getProductWithPrice(Integer id) {
        Optional<Product> product = getProduct(id);

        product.ifPresent(prod -> prod.setPrice(getPrice(id)));

        return product;
    }

    public Optional<Product> saveProduct(Product product) {
        Product created = repository.save(product);
        return Optional.of(created);
    }
}