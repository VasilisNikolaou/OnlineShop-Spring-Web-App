package onlineshop.data;

import org.springframework.data.jpa.repository.JpaRepository;

import onlineshop.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

}
