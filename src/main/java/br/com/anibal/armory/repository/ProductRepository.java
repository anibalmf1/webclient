package br.com.anibal.armory.repository;

import br.com.anibal.armory.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
