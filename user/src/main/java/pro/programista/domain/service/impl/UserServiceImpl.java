package pro.programista.domain.service.impl;

import java.util.UUID;
import pro.programista.domain.entity.User;
import pro.programista.domain.repository.UserRepository;
import pro.programista.domain.service.AbstractCrudService;
import pro.programista.domain.service.UserService;

public class UserServiceImpl extends AbstractCrudService<UUID, User, UserRepository> implements UserService {

  public UserServiceImpl(UserRepository repository) {
    super(repository);
  }

}
