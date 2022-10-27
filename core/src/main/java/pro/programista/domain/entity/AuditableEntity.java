package pro.programista.domain.entity;

import java.io.Serializable;
import java.time.Instant;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AuditableEntity<K extends Serializable> extends DomainEntity<K> {

  Instant createdAt;

  Instant updatedAt;

}
