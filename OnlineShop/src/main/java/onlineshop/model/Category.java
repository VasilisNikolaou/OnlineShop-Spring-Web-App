package onlineshop.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import onlineshop.data.TypeConverter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude= {"products"})
public class Category {

	@Id
	private Long id;
	
	@Column(name = "name")
	@Convert(converter=TypeConverter.class)
	private Type type;
	
	@OneToMany(mappedBy="category")
	private List<Product> products = new ArrayList<>();
	
	public enum Type {
		PHONE, LAPTOP, TELEVISION
	}
		
	
}