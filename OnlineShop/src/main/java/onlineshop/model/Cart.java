package onlineshop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cart implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="total_price")
	private double totalPrice;
	
	@OneToMany(mappedBy="cart", cascade = CascadeType.ALL)
	private List<Item> items = new ArrayList<>();
	

	public void addItem(Item item) {
		  items.add(item);
	      item.setCart(this);
	}
    
	public void calculateTotalPrice() {
		double total = 0;
		
		for(Item item : items) {
			total += item.getProduct().getPrice() * item.getQuantity();
		}
		
		this.setTotalPrice(total);
	}
	
}