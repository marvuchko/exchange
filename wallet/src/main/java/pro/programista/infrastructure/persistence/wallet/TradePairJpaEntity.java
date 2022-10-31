package pro.programista.infrastructure.persistence.wallet;

import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Table(name = "trade_pair")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TradePairJpaEntity {

  @Id
  @GeneratedValue
  UUID id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "first_trade_currency_id", referencedColumnName = "id")
  TradeCurrencyJpaEntity firstTradeCurrency;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "second_trade_currency_id", referencedColumnName = "id")
  TradeCurrencyJpaEntity secondTradeCurrency;

}
