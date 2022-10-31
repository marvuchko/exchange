package pro.programista.infrastructure.persistence.wallet;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Table(name = "trade_currency")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TradeCurrencyJpaEntity {

  @Id
  @GeneratedValue
  UUID id;

  @Column
  String name;

}
