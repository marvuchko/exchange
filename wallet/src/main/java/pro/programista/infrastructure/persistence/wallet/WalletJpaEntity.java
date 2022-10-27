package pro.programista.infrastructure.persistence.wallet;

import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "wallet")
public class WalletJpaEntity {

  @Id
  @GeneratedValue
  UUID id;

  @Column
  BigDecimal balance;

  @Column
  String currency;

}
