package onlineshop.data;

import org.springframework.data.jpa.repository.JpaRepository;

import onlineshop.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
