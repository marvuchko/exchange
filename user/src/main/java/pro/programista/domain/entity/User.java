package pro.programista.domain.entity;

import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class User extends DomainEntity<UUID> {

  String firstName;
  String lastName;
  String email;

}
