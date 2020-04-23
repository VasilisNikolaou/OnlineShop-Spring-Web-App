package onlineshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import onlineshop.model.Order;
import onlineshop.model.Product;

@Entity
@Table(name="order_item")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderItem {
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;
	
	@OneToOne
	@JoinColumn(name="product_id")
	private Product product;

	public OrderItem(int quantity, Product product) {
		super();
		this.quantity = quantity;
		this.product = product;
	}

}
