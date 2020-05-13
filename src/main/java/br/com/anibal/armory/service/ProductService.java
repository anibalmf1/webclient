package br.com.anibal.armory.service;

import br.com.anibal.armory.model.Price;
import br.com.anibal.armory.model.Product;
import br.com.anibal.armory.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Optional;

@Service
public class ProductService {

    final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final String GET_PRICE_URL = "/price/{product_id}";
    private final String SAVE_PRICE_URL = "/price";

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

    private void savePrice(Product product) {
        Price price = new Price();
        price.setProductId(product.getId());
        price.setPrice(product.getPrice());

        priceWebClient
                .post()
                .uri(SAVE_PRICE_URL)
                .body(BodyInserters.fromObject(price))
                .retrieve()
                .bodyToMono(String.class)
                .subscribe();
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

        if (product.getPrice() != null) {
            savePrice(created);
        }

        return Optional.of(created);
    }

}
