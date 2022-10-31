package pro.programista.domain.entity;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import pro.programista.domain.value.TradeCurrency;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TradePair extends DomainEntity<UUID> {

  TradeCurrency firstCurrency;
  TradeCurrency secondCurrency;

}
