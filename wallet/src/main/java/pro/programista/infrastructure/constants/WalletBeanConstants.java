package pro.programista.infrastructure.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class WalletBeanConstants {

  public static final String WALLET_ENTITY_MANAGER = "WALLET:ENTITY_MANAGER";

  public static final String TRANSACTION_MANAGER = "WALLET:TRANSACTION_MANAGER";

  public static final String WALLET_DATA_SOURCE = "WALLET:DATA_SOURCE";

  public static final String DOMAIN_EVENT_BUS = "WALLET:DOMAIN_EVENT_BUS";

  public static final String WALLET_PERSISTENCE = "WALLET:PERSISTENCE";

}
