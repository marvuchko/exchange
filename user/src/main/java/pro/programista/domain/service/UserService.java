package pro.programista.domain.service;

import java.util.UUID;
import pro.programista.domain.entity.User;
import pro.programista.domain.repository.UserRepository;

public interface UserService extends CrudService<UUID, User, UserRepository> {
}
