package pro.programista.infrastructure.persistence.wallet;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletJpaRepository extends JpaRepository<WalletJpaEntity, UUID> {
}
