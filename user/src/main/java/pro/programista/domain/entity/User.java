package pro.programista.domain.entity;

import java.util.UUID;
import lombok.Data;

@Data
public class User extends BaseEntity<UUID> {

  String firstName;
  String lastName;
  String email;

}
