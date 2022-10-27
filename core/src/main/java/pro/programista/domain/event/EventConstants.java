package pro.programista.domain.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EventConstants {

  public static final String EVENT_PUBLISHING_CRON = "${app.events.publish.cron}";

  public static final String DOMAIN_EVENTS = "domain_events";

  public static final String NAME_TAG = "name";

}
