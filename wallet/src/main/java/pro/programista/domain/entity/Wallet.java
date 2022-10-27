package pro.programista.domain.entity;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import pro.programista.domain.value.CurrencyPair;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Wallet extends DomainEntity<UUID> {

  UUID userId;
  CurrencyPair currencyPair;

}
