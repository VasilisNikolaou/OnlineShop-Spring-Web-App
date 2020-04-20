package onlineshop.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;


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
	

}
