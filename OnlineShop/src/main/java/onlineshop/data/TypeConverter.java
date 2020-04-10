package onlineshop.data;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


import onlineshop.model.Category.Type;


@Converter
public class TypeConverter implements AttributeConverter<Type, String>{

	@Override
	public String convertToDatabaseColumn(Type attribute) {
		return null;
	}

	@Override
	public Type convertToEntityAttribute(String dbData) {
		return Type.valueOf(dbData);
		
	}	

}
