package pro.programista.domain.event;

import java.time.Instant;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public abstract class Event<K> {

  K eventId;
  Instant eventTimestamp;
  String eventGroupId;

}
