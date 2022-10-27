package pro.programista.infrastructure.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserBeanConstants {

  public static final String DOMAIN_EVENT_BUS = "USER:DOMAIN_EVENT_BUS";

  public static final String CACHE = "USER:CACHE";

  public static final String USER_REPOSITORY = "USER:USER_REPOSITORY";

  public static final String USER_SERVICE = "USER:USER_SERVICE";

  public static final String USER_DATA_SOURCE = "USER:DATA_SOURCE";

  public static final String USER_ENTITY_MANAGER = "USER:ENTITY_MANAGER";

  public static final String TRANSACTION_MANAGER = "USER:TRANSACTION_MANAGER";

  public static final String USER_DOMAIN_EVENTS = "USER:USER_DOMAIN_EVENTS";

  public static final String USER_PERSISTENCE = "USER:PERSISTENCE";

}
