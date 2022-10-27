package pro.programista.domain.event;

import java.io.Serializable;
import java.time.Instant;

public interface DomainEvent<K extends Serializable> extends Serializable {

  K aggregateId();

  Instant creationTimestamp();

  Instant publishTimestamp();

}
