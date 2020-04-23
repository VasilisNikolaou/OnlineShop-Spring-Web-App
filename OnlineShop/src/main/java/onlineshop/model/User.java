package onlineshop.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import onlineshop.model.Cart;
import onlineshop.model.Order;


public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable=false, unique=true)
	@NotBlank
	private String username;
	
	@Column(nullable=false, name="first_name")
	@NotBlank
	private String firstName;
	
	@Column(nullable=false, name="last_name")
	@NotBlank
	private String lastName;
	
	@Column(nullable=false)
	@NotBlank
	private String password;
	
	@Column(nullable=false)
	@NotBlank
	private String email;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Order> orders = new ArrayList<>();
	
	@OneToOne
	@JoinColumn(name="cart_id")
	private Cart cart;

	public User(@NotEmpty String username, @NotEmpty String firstName, @NotEmpty String lastName,
			@NotEmpty String password, @NotEmpty String email) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
	}

	public void addOrder(Order order) {
		this.orders.add(order);
		order.setUser(this);
	}

}
