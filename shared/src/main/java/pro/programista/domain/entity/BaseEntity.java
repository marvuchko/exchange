package pro.programista.domain.entity;

import java.io.Serializable;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseEntity<K extends Serializable> implements Serializable {

  K id;

}
