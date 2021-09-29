package app.converter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.persistence.AttributeConverter;

public class CartStatusConverter implements AttributeConverter<CartStatus, String> {

    private final static Map<CartStatus, String> RELATIONS = new HashMap<>();

    static {
        RELATIONS.put(CartStatus.ACTIVE, "A");
        RELATIONS.put(CartStatus.CANCELED, "C");
        RELATIONS.put(CartStatus.FINISHED, "F");
    }

    @Override
    public String convertToDatabaseColumn(CartStatus attribute) {
        return RELATIONS.get(attribute);
    }

    @Override
    public CartStatus convertToEntityAttribute(String dbData) {
        Optional<CartStatus> optional = RELATIONS
                .entrySet()
                .stream()
                .filter(it -> it.getValue().equals(dbData))
                .map(Map.Entry::getKey)
                .findAny();
        if (!optional.isPresent()) {
            throw new IllegalArgumentException("CartStatus " + dbData + " not supported yet!");
        }
        return optional.get();
    }
}
