package pro.programista.infrastructure.persistence.wallet;

import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user_wallet")
public class WalletJpaEntity {

  @Id
  @GeneratedValue
  UUID id;

  @Column
  BigDecimal balance;

  @Column
  @Enumerated
  WalletCurrency walletCurrency;

}
