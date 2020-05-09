package onlineshop.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import onlineshop.model.OrderItem;
import onlineshop.model.User;

@Entity
@Table(name="orders")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message="Zip is required")
	private String zip;
	
	@NotBlank(message="Street is required")
	private String street;
	
	@NotBlank(message="City is required")
	private String city;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
    
    private double total_price;
    
    @OneToMany(mappedBy="order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();
    
    public void addOrderItem(OrderItem item) {
    	 
    	 orderItems.add(item);
    	 item.setOrder(this);
    }
}
