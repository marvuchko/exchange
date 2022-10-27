package pro.programista.domain.repository;

import java.util.UUID;
import pro.programista.domain.entity.User;

public interface UserRepository extends DomainRepository<UUID, User> {
}
