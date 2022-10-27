package pro.programista.domain.entity;

import java.io.Serializable;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SoftDeletableEntity<K extends Serializable> extends AuditableEntity<K> {

  Instant deletedAt;

  boolean deleted;

}
